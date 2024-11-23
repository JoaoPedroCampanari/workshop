package com.educandoweb.course.controllers;

import com.educandoweb.course.domain.dtos.UsersDto;
import com.educandoweb.course.domain.entities.Orders;
import com.educandoweb.course.domain.entities.Users;
import com.educandoweb.course.services.impl.UsersServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Users")
public class UsersController {

    private final UsersServices usersServices;

    public UsersController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @PostMapping
    public ResponseEntity<Users> save(@RequestBody UsersDto usersDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usersServices.save(usersDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(usersServices.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usersServices.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(usersServices.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable(value = "id") UUID id, @RequestBody UsersDto usersDto){
        return ResponseEntity.status(HttpStatus.OK).body(usersServices.update(id,usersDto));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<Orders>> findAllOrderByUserId(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(usersServices.findAllOrderByUserId(id));
    }
}
