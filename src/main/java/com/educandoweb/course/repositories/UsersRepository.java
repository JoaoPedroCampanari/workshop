package com.educandoweb.course.repositories;

import com.educandoweb.course.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    boolean existsByEmail(String email);

}
