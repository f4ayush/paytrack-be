package com.paytrack.payments.entity;

import com.paytrack.payments.enums.PaymentMethod;
import com.paytrack.payments.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

public class Payment {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private  String id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    private float amount;
    private LocalDateTime timeStamp;
    private PaymentMethod method;
    private PaymentStatus status;
}
