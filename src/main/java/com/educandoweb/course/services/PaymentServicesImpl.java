package com.educandoweb.course.services;

import com.educandoweb.course.domain.dtos.PaymentDto;
import com.educandoweb.course.domain.entities.OrderStatus;
import com.educandoweb.course.domain.entities.Orders;
import com.educandoweb.course.domain.entities.Payment;
import com.educandoweb.course.exception.payment.PaymentNotFoundException;
import com.educandoweb.course.repositories.OrdersRepository;
import com.educandoweb.course.repositories.PaymentRepository;
import com.educandoweb.course.services.impl.PaymentServices;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentServicesImpl implements PaymentServices {

    private final PaymentRepository paymentRepository;
    private final OrdersRepository ordersRepository;

    public PaymentServicesImpl(PaymentRepository paymentRepository, OrdersRepository ordersRepository) {
        this.paymentRepository = paymentRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Payment save(PaymentDto paymentDto) {
        Orders orders = ordersRepository.findById(paymentDto.getId_Orders()).orElseThrow(() -> new PaymentNotFoundException("ID_Order don't exist!"));

        Payment payment = new Payment();

        BeanUtils.copyProperties(paymentDto, payment);

        orders.setPayment(payment);
        orders.setOrderStatus(OrderStatus.PAID);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment findById(UUID id) {
        return paymentRepository.findById(id).orElseThrow(()-> new PaymentNotFoundException("Payment not found with id:" + id));
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public String deleteById(UUID id) {
        try{
            paymentRepository.deleteById(id);
            return "Payment deleted successfully";
        }
        catch (EmptyResultDataAccessException e){
            throw new PaymentNotFoundException("Payment not found with id:" + id);
        }
    }
}
