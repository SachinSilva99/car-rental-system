package com.carentalsystem.dto;

import com.carentalsystem.util.enums.payemntstatus.PaymentStatus;

import java.time.LocalDateTime;

public class RentalRequestDTO {
    private String requestId;

    private LocalDateTime pickUp;

    private LocalDateTime returnDateTime;

    private String pickUpVenue;

    private String returnVenue;

    private boolean isDriverRequested;

    private PaymentStatus paymentStatus;

    private String carId;

    private String bankSlipImg;

    private String customerId;

}
