package com.carentalsystem.dto;

import com.carentalsystem.util.enums.cartype.CarType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@ApiModel(description = "Represents a car.")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDTO {
    private String id;

    private String description;

    private CarType carType;

    private double dailyRate;

    private int freeKmsPerDay;

    private double monthlyRate;

    private int freeKmsPerMonth;
}
