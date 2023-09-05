package com.carentalsystem.dto;

import com.carentalsystem.util.enums.cartype.CarType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private byte[] img1;
    private byte[] img2;
    private byte[] img3;
    private byte[] img4;
}
