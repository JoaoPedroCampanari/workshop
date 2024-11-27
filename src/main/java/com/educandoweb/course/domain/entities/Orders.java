package com.educandoweb.course.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private final LocalDateTime moment = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.WAITING_PAYMENT;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Users client;
    
    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    private Payment payment;


}
