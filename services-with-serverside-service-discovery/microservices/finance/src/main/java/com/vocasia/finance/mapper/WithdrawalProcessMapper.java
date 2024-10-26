package com.vocasia.finance.mapper;

import com.vocasia.finance.config.AwsConfigProperties;
import com.vocasia.finance.dto.data.WithdrawalProcessDto;
import com.vocasia.finance.entity.WithdrawalProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class WithdrawalProcessMapper {

    private static AwsConfigProperties awsConfigProperties;

    @Autowired
    public WithdrawalProcessMapper(AwsConfigProperties awsConfigProperties, ApplicationContext applicationContext) {
        WithdrawalProcessMapper.awsConfigProperties = awsConfigProperties;
    }

    public static WithdrawalProcessDto mapToDto(WithdrawalProcess withdrawalProcess) {
        WithdrawalProcessDto dto = new WithdrawalProcessDto();

        dto.setId(withdrawalProcess.getId());
        dto.setWithdrawalRequestId(withdrawalProcess.getWithdrawalRequestId());
        dto.setAmount(withdrawalProcess.getAmount());
        dto.setProcessedAt(withdrawalProcess.getProcessedAt());
        dto.setProofDocument(withdrawalProcess.getProofDocument());

        if (withdrawalProcess.getProofDocument() != null) {
            String bucketUrl = String.format("https://%s.s3.%s.amazonaws.com/", awsConfigProperties.getS3().getBucket(), awsConfigProperties.getS3().getRegion());
            String proofDocumentUrl = bucketUrl + withdrawalProcess.getProofDocument();

            dto.setProofDocumentUrl(proofDocumentUrl);
        }

        dto.setNote(withdrawalProcess.getNote());
        dto.setStatus(withdrawalProcess.getStatus());
        dto.setCreatedAt(withdrawalProcess.getCreatedAt());
        dto.setUpdatedAt(withdrawalProcess.getUpdatedAt());

        return dto;
    }

    public static WithdrawalProcess mapToEntity(WithdrawalProcessDto dto) {
        WithdrawalProcess withdrawalProcess = new WithdrawalProcess();

        withdrawalProcess.setId(dto.getId());
        withdrawalProcess.setWithdrawalRequestId(dto.getWithdrawalRequestId());
        withdrawalProcess.setAmount(dto.getAmount());
        withdrawalProcess.setProcessedAt(dto.getProcessedAt());
        withdrawalProcess.setProofDocument(dto.getProofDocument());
        withdrawalProcess.setNote(dto.getNote());
        withdrawalProcess.setStatus(dto.getStatus());
        withdrawalProcess.setCreatedAt(dto.getCreatedAt());
        withdrawalProcess.setUpdatedAt(dto.getUpdatedAt());

        return withdrawalProcess;
    }

}
