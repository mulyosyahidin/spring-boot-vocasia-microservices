package com.vocasia.finance.service.impl;

import com.vocasia.finance.config.AwsConfigProperties;
import com.vocasia.finance.entity.WithdrawalProcess;
import com.vocasia.finance.packages.aws.service.IAwsService;
import com.vocasia.finance.repository.WithdrawalProcessRepository;
import com.vocasia.finance.request.WithdrawalProcessRequest;
import com.vocasia.finance.service.IWithdrawalProcessService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class WithdrawalProcessServiceImpl implements IWithdrawalProcessService {

    private final AwsConfigProperties awsConfigProperties;
    private final IAwsService awsService;
    private WithdrawalProcessRepository withdrawalProcessRepository;

    @Override
    public WithdrawalProcess save(Long withdrawalRequestId, WithdrawalProcessRequest withdrawalProcessRequest) throws IOException {
        WithdrawalProcess withdrawalProcess = new WithdrawalProcess();

        withdrawalProcess.setWithdrawalRequestId(withdrawalRequestId);
        withdrawalProcess.setAmount(withdrawalProcessRequest.getAmount());
        withdrawalProcess.setProcessedAt(LocalDateTime.now());
        withdrawalProcess.setNote(withdrawalProcessRequest.getNote());
        withdrawalProcess.setStatus("completed");

        if (withdrawalProcessRequest.getProofDocument() != null) {
            String bucketName = awsConfigProperties.getS3().getBucket();
            MultipartFile proofDocument = withdrawalProcessRequest.getProofDocument();

            String currentTimeInMilli = String.valueOf(System.currentTimeMillis());
            String fileName = currentTimeInMilli + "_"+StringUtils.cleanPath(Objects.requireNonNull(proofDocument.getOriginalFilename()));

            String contentType = proofDocument.getContentType();
            long fileSize = proofDocument.getSize();
            InputStream inputStream = proofDocument.getInputStream();

            awsService.uploadFile(bucketName, fileName, fileSize, contentType, inputStream);

            withdrawalProcess.setProofDocument(fileName);
        }

        return withdrawalProcessRepository.save(withdrawalProcess);
    }

    @Override
    public WithdrawalProcess findByWithdrawalRequestId(Long id) {
        return withdrawalProcessRepository.findByWithdrawalRequestId(id);
    }

}
