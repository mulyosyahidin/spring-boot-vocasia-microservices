import pandas as pd
import os
import matplotlib.pyplot as plt

base_path = "/content/drive/MyDrive/SKRIPSI/DATA2"


def read_file(service, resource):
    # Definisikan base path

    # Mengubah direktori ke folder yang ditentukan
    folder_path = base_path
    os.chdir(folder_path)

    # Format nama file
    FILE_FORMAT = "({SERVICE} - {RESOURCE}.csv"

    # Membuat daftar untuk menyimpan data dari semua file
    all_data = []

    # Format nama file dengan menggantikan placeholder
    file_name = FILE_FORMAT.format(SERVICE=service, RESOURCE=resource)

    # Cek apakah file ada di folder raw
    if os.path.exists(file_name):
        try:
            # Membaca file CSV menggunakan pandas
            data = pd.read_csv(file_name)

            # Membuat daftar waktu baru mulai dari 00:14, bertambah setiap 15 detik
            time_list = []
            start_time = 14  # Dimulai dari 00:14
            for i in range(len(data)):
                minutes = (start_time + i * 15) // 60
                seconds = (start_time + i * 15) % 60
                time_list.append(f"{minutes:02}:{seconds:02}")

            # Mengganti kolom 'Time' dengan waktu baru
            data["Time"] = time_list

            # Menambahkan kolom untuk nomor pengujian (untuk membedakan file-file tersebut)
            data["Test"] = f"Pengujian {number}"  # Menambahkan kolom untuk visualisasi

            # Menambahkan data ke dalam daftar all_data
            all_data.append(data)

            # Hitung MIN, MAX, dan AVG untuk kolom Usage per file
            min_usage = data["Usage"].min()
            max_usage = data["Usage"].max()
            avg_usage = data["Usage"].mean()

            # Menambahkan ke overall usage untuk perhitungan global
            overall_min_usage = min(overall_min_usage, min_usage)
            overall_max_usage = max(overall_max_usage, max_usage)
            overall_sum_usage += data["Usage"].sum()
            overall_count_usage += len(data)

            # Tampilkan MIN, MAX, AVG per file
            print(
                f"Pengujian {number} - MIN: {min_usage:.4f} MAX: {max_usage:.4f} AVG: {avg_usage:.4f}"
            )

            # Menyimpan hasil ke folder 'results/' dengan nama yang sama
            results_folder = os.path.join(base_path, folder, "results")
            if not os.path.exists(results_folder):
                os.makedirs(results_folder)  # Membuat folder jika belum ada

            # Menentukan path file hasil yang baru
            result_file_path = os.path.join(results_folder, file_name)

            # Menghapus file lama jika ada
            if os.path.exists(result_file_path):
                os.remove(result_file_path)

            # Menyimpan data ke file baru di folder results
            data.to_csv(result_file_path, index=False)
        except Exception as e:
            print(f"Terjadi kesalahan saat membaca {file_name}: {e}")
    else:
        print(f"File {file_name} tidak ditemukan.")


# Fungsi untuk plot chart
def plot_usage(service, resource):
    print(f"Service: {service} | Resource: {resource}")
    res = resource.upper()

    # Ambil data dengan memanggil fungsi read_file
    data = read_file(service, resource)

    if data is not None:
        # Plotting
        plt.figure(figsize=(10, 6))

        # Membuat plot untuk setiap pengujian yang ada di kolom 'Test'
        for number in range(1, 4):  # Menggunakan 'number' untuk membedakan pengujian
            subset = data[data["Test"] == f"Pengujian {number}"]
            plt.plot(
                subset["Time"],
                subset["Usage"],
                marker="o",
                linestyle="-",
                label=f"Pengujian {number}",
            )

        # Menambahkan judul dan label
        plt.xlabel("Time")
        plt.ylabel(f"{res} Usage")
        plt.legend()
        plt.grid(True)

        plt.xticks(rotation=45)

        # Membuat folder chart jika belum ada
        if not os.path.exists(base_path):
            os.makedirs(base_path)

        # Menyimpan chart ke folder 'chart' di dalam folder {folder} dengan nama berdasarkan variabel folder
        chart_file_path = os.path.join(base_path, f"{folder}-{resource}.png")
        plt.savefig(chart_file_path)

        # Menampilkan chart
        plt.show()


service = "auth"
resource = "cpu"

plot_usage(service, resource)
