package com.carentalsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleID;


    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne
    private RentalRequest rentalRequest;
}
