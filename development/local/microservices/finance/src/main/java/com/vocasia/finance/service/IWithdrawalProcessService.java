package com.vocasia.finance.service;

import com.vocasia.finance.entity.WithdrawalProcess;
import com.vocasia.finance.request.WithdrawalProcessRequest;

import java.io.IOException;

public interface IWithdrawalProcessService {

    WithdrawalProcess save(Long withdrawalRequestId, WithdrawalProcessRequest withdrawalProcessRequest) throws IOException;
    WithdrawalProcess findByWithdrawalRequestId(Long id);

}
