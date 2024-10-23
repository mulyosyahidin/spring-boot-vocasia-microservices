package com.vocasia.finance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WithdrawalRequest {

    @NotNull(message = "Amount tidak boleh kosong")
    @DecimalMin(value = "0.01", message = "Amount harus lebih besar dari 0")
    private Double amount;

    @Size(max = 255, message = "Remarks tidak boleh lebih dari 255 karakter")
    private String remarks;

    @NotBlank(message = "Nama bank tidak boleh kosong")
    @Size(max = 100, message = "Nama bank tidak boleh lebih dari 100 karakter")
    @JsonProperty("bank_name")
    private String bankName;

    @NotBlank(message = "Nomor rekening tidak boleh kosong")
    @Size(max = 20, message = "Nomor rekening tidak boleh lebih dari 20 karakter")
    @Pattern(regexp = "\\d+", message = "Nomor rekening harus berupa angka")
    @JsonProperty("bank_account_number")
    private String bankAccountNumber;

    @NotBlank(message = "Nama pemilik rekening tidak boleh kosong")
    @Size(max = 100, message = "Nama pemilik rekening tidak boleh lebih dari 100 karakter")
    @JsonProperty("bank_account_name")
    private String bankAccountName;

}
