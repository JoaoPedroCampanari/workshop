package com.educandoweb.course.repositories;

import com.educandoweb.course.domain.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}
