package com.vocasia.authentication.repository;

import com.vocasia.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Check if the email is already registered
     *
     * @param email email to check
     * @return boolean
     */
    boolean existsByEmail(String email);

    /**
     * Check if the username is already registered
     *
     * @param username username to check
     * @return boolean
     */
    boolean existsByUsername(String username);
}
