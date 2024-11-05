import {Charts} from "../EarningChart/Partials/Charts.jsx";

export const WaitingStatus = ({status}) => {
    return (
        <div className="rounded-16 bg-white -dark-bg-dark-1 shadow-4 h-100">
            <div className="d-flex justify-between items-center py-20 px-30 border-bottom-light">
                <h2 className="text-17 lh-1 fw-500">Pendaftaran Menunggu Konfirmasi</h2>
            </div>
            <div className="py-40 px-30">
                {
                    status === 'pending' ? 'Pendaftaran anda menunggu konfirmasi Admin. Mohon menunggu sampai admin memberikan konfirmasi.'
                        : 'Pendaftaran anda ditolak oleh Admin. Silahkan hubungi Admin untuk informasi lebih lanjut.'
                }
            </div>
        </div>
    );
}