package com.carentalsystem.service;

import com.carentalsystem.dto.CarDTO;
import com.carentalsystem.service.exception.DuplicationException;
import com.carentalsystem.service.exception.InUseException;

public interface CarService {
    String create(CarDTO carDTO) throws DuplicationException;

    CarDTO get(String id) throws ClassNotFoundException;

    void update(CarDTO carDTO, String id)throws ClassNotFoundException;

    void delete(String id) throws InUseException, ClassNotFoundException;
}
