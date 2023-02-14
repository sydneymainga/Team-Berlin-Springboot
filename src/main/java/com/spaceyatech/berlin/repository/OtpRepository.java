package com.spaceyatech.berlin.repository;

import com.spaceyatech.berlin.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OtpRepository extends JpaRepository<User, UUID> {

}
