package com.vocasia.authentication.service;

import com.vocasia.authentication.request.CreatePasswordRequest;
import com.vocasia.authentication.request.ForgetPasswordRequest;

public interface IForgetPasswordService {

    void requestForgotPassword(ForgetPasswordRequest forgetPasswordRequest);
    String getEmailOfRequest(CreatePasswordRequest createPasswordRequest);

}
