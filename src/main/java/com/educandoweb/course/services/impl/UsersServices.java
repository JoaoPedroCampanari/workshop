package com.educandoweb.course.services.impl;

import com.educandoweb.course.domain.dtos.UsersDto;
import com.educandoweb.course.domain.entities.Orders;
import com.educandoweb.course.domain.entities.Users;

import java.util.List;
import java.util.UUID;

public interface UsersServices {

    List<Users> findAll();

    Users findById(UUID id);

    Users save(UsersDto usersDto);

    String deleteById(UUID id);

    Users update(UUID id, UsersDto usersDto);

    List<Orders> findAllOrderByUserId(UUID id);

}
