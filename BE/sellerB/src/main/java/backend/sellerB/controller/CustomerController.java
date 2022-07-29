package backend.sellerB.controller;

import backend.sellerB.dto.CustomerDto;
import backend.sellerB.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.create(customerDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<CustomerDto> getCustomerDetail(@PathVariable Integer seq) {
        return ResponseEntity.ok(customerService.getCustomerDetail(seq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDto>> getCustomerList(HttpServletRequest request) {
        return ResponseEntity.ok(customerService.getCustomerList());
    }

    @PutMapping("/{seq}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto, @PathVariable Integer seq) {
        return ResponseEntity.ok(customerService.update(seq, customerDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Integer seq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(customerService.deleteCustomer(seq));
    }
}
