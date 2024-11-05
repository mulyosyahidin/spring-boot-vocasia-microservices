package com.vocasia.authentication.repository;

import com.vocasia.authentication.entity.ForgetPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword, Long> {

    ForgetPassword findByToken(String token);

}
