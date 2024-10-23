package com.vocasia.finance.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class WithdrawalProcessDto {

    private Long id;

    @JsonProperty("withdrawal_request_id")
    private Long withdrawalRequestId;

    private Double amount;

    @JsonProperty("processed_at")
    private LocalDateTime processedAt;

    @JsonProperty("proof_document")
    private String proofDocument;

    @JsonProperty("proof_document_url")
    private String proofDocumentUrl;

    private String note;

    private String status;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
