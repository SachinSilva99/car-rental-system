package com.carentalsystem.api;

import com.carentalsystem.dto.CustomerDTO;
import com.carentalsystem.service.CustomerService;
import com.carentalsystem.util.enums.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/crs/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> create(@RequestBody CustomerDTO dto) {
        String id = customerService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        204,
                        id,
                        null
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> get(@PathVariable String id) throws ClassNotFoundException {
        CustomerDTO customerDTO = customerService.get(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Ok",
                        customerDTO
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> update(@PathVariable String id, @RequestBody CustomerDTO dto) throws ClassNotFoundException {
        customerService.update(dto, id);
        return new ResponseEntity<>(
                new StandardResponse(),
                HttpStatus.NO_CONTENT
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id) throws ClassNotFoundException {
        customerService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/{pageNumber}/{noOfCustomers}")
    public ResponseEntity<StandardResponse> findAll(@PathVariable int noOfCustomers, @PathVariable int pageNumber) {
        Page<CustomerDTO> dtoPage = customerService.findAll(pageNumber, noOfCustomers);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "ok",
                        dtoPage
                ),
                HttpStatus.OK
        );
    }
}
