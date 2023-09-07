package com.carentalsystem.service;

import com.carentalsystem.dto.DriverDTO;
import com.carentalsystem.dto.RentalRequestDTO;

public interface PlaceRentalRequest {
    String placeRentalRequest(RentalRequestDTO rentalRequestDTO, DriverDTO driverDTO) throws ClassNotFoundException;
}
