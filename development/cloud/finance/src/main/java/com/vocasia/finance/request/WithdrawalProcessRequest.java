package com.vocasia.finance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class WithdrawalProcessRequest {

    @NotNull(message = "File dokumen bukti harus diisi")
    @JsonProperty("proof_document")
    private MultipartFile proofDocument;

    @NotNull(message = "Nominal penarikan harus diisi")
    private Double amount;

    @NotBlank(message = "Keterangan penarikan harus diisi")
    private String note;

}
