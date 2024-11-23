package com.educandoweb.course.services.impl;

import com.educandoweb.course.domain.dtos.OrdersDto;
import com.educandoweb.course.domain.entities.OrderStatus;
import com.educandoweb.course.domain.entities.Orders;
import com.educandoweb.course.domain.entities.Users;

import java.util.List;
import java.util.UUID;

public interface OrdersServices {

    Orders findById(UUID id);

    List<Orders> findAll();

    Orders update(UUID id, OrdersDto ordersDto);

    String updateOrderStatus(UUID id, OrderStatus status);

    Orders save(OrdersDto ordersDto);

    Users findClientByOrderId(UUID id);

}
