package com.paytrack.payments.repository;

import com.paytrack.payments.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
