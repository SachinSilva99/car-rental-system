package com.carentalsystem.dto;

import com.carentalsystem.entity.Driver;
import com.carentalsystem.entity.RentalRequest;
import jakarta.persistence.*;

public class ScheduleDTO {

    private Long scheduleID;

    private String driverId;


    private String rentalRequestId;
}
