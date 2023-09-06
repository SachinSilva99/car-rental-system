package com.carentalsystem.service;

import com.carentalsystem.dto.CustomerDTO;
import com.carentalsystem.service.exception.InUseException;

public interface CustomerService {
    String create(CustomerDTO customerDTO);

    CustomerDTO get(String id) throws ClassNotFoundException;

    void update(CustomerDTO customerDTO, String id) throws ClassNotFoundException;

    void delete(String id) throws InUseException, ClassNotFoundException;
}
