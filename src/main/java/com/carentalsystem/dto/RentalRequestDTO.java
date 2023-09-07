package com.carentalsystem.dto;

import com.carentalsystem.util.enums.payemntstatus.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalRequestDTO {
    private String requestId;

    private LocalDateTime pickUp;

    private LocalDateTime returnDateTime;

    private String pickUpVenue;

    private String returnVenue;

    private boolean isDriverRequested;

    private PaymentStatus paymentStatus;

    private String carId;

    private byte[] bankSlipImg;

    private String customerId;

}
