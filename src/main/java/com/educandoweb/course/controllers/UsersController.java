package com.educandoweb.course.controllers;

import com.educandoweb.course.domain.dtos.UsersDto;
import com.educandoweb.course.domain.entities.Users;
import com.educandoweb.course.services.impl.UsersServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Users")
public class UsersController {

    private final UsersServices usersServices;

    public UsersController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @PutMapping
    public ResponseEntity<Users> save(@RequestBody UsersDto usersDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usersServices.save(usersDto));
    }

    @GetMapping
    public ResponseEntity<Users> findById(UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(usersServices.findById(id));
    }
}
