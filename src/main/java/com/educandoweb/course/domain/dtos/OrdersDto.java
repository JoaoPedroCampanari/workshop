package com.educandoweb.course.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {

    private String orderStatus;
    private UUID id_client;
    private UUID id_payment;

}
