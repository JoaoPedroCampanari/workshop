package com.educandoweb.course.services;

import com.educandoweb.course.domain.dtos.PaymentDto;
import com.educandoweb.course.domain.entities.Orders;
import com.educandoweb.course.domain.entities.Payment;
import com.educandoweb.course.repositories.OrdersRepository;
import com.educandoweb.course.repositories.PaymentRepository;
import com.educandoweb.course.services.impl.PaymentServices;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

public class PaymentServicesImpl implements PaymentServices {

    private final PaymentRepository paymentRepository;
    private final OrdersRepository ordersRepository;

    public PaymentServicesImpl(PaymentRepository paymentRepository, OrdersRepository ordersRepository) {
        this.paymentRepository = paymentRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Payment save(PaymentDto paymentDto) {
        Orders orders = ordersRepository.findById(paymentDto.getId_Orders()).orElseThrow(() -> new IllegalArgumentException("ID_Order don't exist!"));

        Payment payment = new Payment();

        BeanUtils.copyProperties(paymentDto, payment);

        orders.setPayment(payment);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment findById(UUID id) {
        return null;
    }

    @Override
    public List<Payment> findAll() {
        return List.of();
    }

    @Override
    public String deleteById(UUID id) {
        return "";
    }
}
