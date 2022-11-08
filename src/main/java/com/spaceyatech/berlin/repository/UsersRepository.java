package com.spaceyatech.berlin.repository;

import com.spaceyatech.berlin.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
// TODO: 06/11/2022 interacts with db queries

}
