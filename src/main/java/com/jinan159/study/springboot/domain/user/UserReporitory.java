package com.jinan159.study.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReporitory extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
