package com.educandoweb.course.services.impl;

import com.educandoweb.course.domain.dtos.PaymentDto;
import com.educandoweb.course.domain.entities.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentServices {

    Payment save(PaymentDto paymentDto);

    Payment findById(UUID id);

    List<Payment> findAll();

    String deleteById(UUID id);
}
