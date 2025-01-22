package com.vocasia.authentication.repository;

import com.vocasia.authentication.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User findByEmail(String email);

    List<User> getByRole(String role);

    Page<User> findAllByRole(String role, Pageable paging);

    long countByRole(String role);

}
