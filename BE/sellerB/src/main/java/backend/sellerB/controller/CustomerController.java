package backend.sellerB.controller;

import backend.sellerB.dto.CustomerDto;
import backend.sellerB.dto.EditCustomerDto;
import backend.sellerB.dto.EditCustomerTokenDto;
import backend.sellerB.repository.CustomerRepository;
import backend.sellerB.service.CustomerService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@DynamicUpdate
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;


    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.signup(customerDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<CustomerDto> getCustomerDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(customerService.getCustomerDetail(seq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDto>> getCustomerList(HttpServletRequest request) {
        return ResponseEntity.ok(customerService.getCustomerList());
    }

    @PutMapping("token/{seq}")
    public ResponseEntity<CustomerDto> updateCustomerToken(@Valid @RequestBody EditCustomerTokenDto editCustomerTokenDto, @PathVariable("seq") Long seq) {
        System.out.println(seq);
        System.out.println(editCustomerTokenDto.getCustomerToken());
        return ResponseEntity.ok(customerService.updateCustomerToken(seq, editCustomerTokenDto));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody EditCustomerDto editCustomerDto, @PathVariable Long seq) {
        return ResponseEntity.ok(customerService.updateCustomer(seq, editCustomerDto));
    }



    @DeleteMapping("/{seq}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Long seq) {
        return ResponseEntity.ok(customerService.deleteCustomer(seq));
    }
}
