package com.carentalsystem.api;

import com.carentalsystem.dto.CustomerDTO;
import com.carentalsystem.service.CustomerService;
import com.carentalsystem.util.enums.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/crs/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> create(@RequestBody CustomerDTO dto) {
        String id = customerService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        HttpStatus.CREATED.value(),
                        id,
                        null
                ),
                HttpStatus.CREATED
        );
    }
}
