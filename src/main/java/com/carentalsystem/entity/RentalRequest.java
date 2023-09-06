package com.carentalsystem.entity;

import com.carentalsystem.util.enums.payemntstatus.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RentalRequest implements Serializable {
    @Id
    private String requestId;

    @Column(nullable = false)
    private LocalDateTime pickUp;

    @Column(nullable = false)
    private LocalDateTime returnDateTime;

    @Column(nullable = false)
    private String pickUpVenue;

    @Column(nullable = false)
    private String returnVenue;

    @Column(nullable = false)
    private boolean isDriverRequested;

    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @ManyToOne
    private Car car;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",nullable = false)
    private byte[] bankSlipImg;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "rentalRequest")
    private Schedule schedule;
}
