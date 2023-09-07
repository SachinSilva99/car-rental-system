package com.carentalsystem.service;

import com.carentalsystem.dto.CustomerDTO;
import com.carentalsystem.dto.DriverDTO;
import com.carentalsystem.service.exception.DuplicationException;
import com.carentalsystem.service.exception.InUseException;
import org.springframework.data.domain.Page;

public interface DriverService {
    String create(DriverDTO driverDTO) throws DuplicationException;

    DriverDTO get(String id) throws ClassNotFoundException;

    void update(DriverDTO driverDTO, String id)throws ClassNotFoundException;

    void delete(String id) throws InUseException, ClassNotFoundException;
    Page<DriverDTO> findAll(int page, int noOfCars);

}
