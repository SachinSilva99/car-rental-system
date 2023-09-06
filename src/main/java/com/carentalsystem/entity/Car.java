package com.carentalsystem.entity;

import com.carentalsystem.util.enums.cartype.CarType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Car implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType carType;

    @Column(nullable = false)
    private double dailyRate;

    @Column(nullable = false)
    private int freeKmsPerDay;

    @Column(nullable = false)
    private double monthlyRate;

    @Column(nullable = false)
    private int freeKmsPerMonth;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",nullable = false)
    private byte[] img1;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",nullable = false)
    private byte[] img2;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",nullable = false)
    private byte[] img3;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",nullable = false)
    private byte[] img4;

    @OneToMany(mappedBy = "car")
    private List<RentalRequest> rentalRequestList = new ArrayList<>();
}
