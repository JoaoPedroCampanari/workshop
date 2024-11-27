package com.educandoweb.course.controllers;

import com.educandoweb.course.domain.dtos.PaymentDto;
import com.educandoweb.course.domain.entities.Payment;
import com.educandoweb.course.services.impl.PaymentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Payment")
public class PaymentController {

    private final PaymentServices paymentServices;

    public PaymentController(PaymentServices paymentServices) {
        this.paymentServices = paymentServices;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable(name = "id") UUID id){

        return ResponseEntity.status(HttpStatus.OK).body(paymentServices.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findByAll(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentServices.findAll());
    }

    @PostMapping
    public ResponseEntity<Payment> save (@RequestBody PaymentDto paymentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentServices.save(paymentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(paymentServices.deleteById(id));
    }
}
