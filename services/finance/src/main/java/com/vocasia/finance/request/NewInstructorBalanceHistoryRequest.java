package com.vocasia.finance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewInstructorBalanceHistoryRequest {

    @NotNull(message = "ID Instruktur tidak boleh kosong")
    @JsonProperty("instructor_id")
    private Long instructorId;

    @NotBlank(message = "Tipe transaksi tidak boleh kosong")
    @JsonProperty("type")
    private String type;

    @NotNull(message = "Jumlah tidak boleh kosong")
    @JsonProperty("amount")
    private Double amount;

    @NotNull(message = "Fee platform tidak boleh kosong")
    @JsonProperty("platform_fee")
    private Double platformFee;

    @JsonProperty("reference_id")
    private Long referenceId;

    @JsonProperty("reference_type")
    private String referenceType;

    @JsonProperty("transaction_status")
    private String transactionStatus;

    @JsonProperty("remarks")
    private String remarks;

}
