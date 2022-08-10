package backend.sellerB.controller;

import backend.sellerB.dto.CustomerWaitingPageDto;
import backend.sellerB.service.CustomerWaitingPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/waiting-page")
public class CustomerWaitingPageController {
    private final CustomerWaitingPageService customerWaitingPageService;

    public CustomerWaitingPageController(CustomerWaitingPageService customerWaitingPageService) {
        this.customerWaitingPageService = customerWaitingPageService;
    }

    @PostMapping
    public ResponseEntity<CustomerWaitingPageDto> saveCustomerWaitingPage(@Valid @RequestBody CustomerWaitingPageDto customerWaitingPageDto) {
        return ResponseEntity.ok(customerWaitingPageService.create(customerWaitingPageDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<CustomerWaitingPageDto> getCustomerWaitingPageDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(customerWaitingPageService.getCustomerWaitingPageDetail(seq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerWaitingPageDto>> getCustomerWaitingPageList(HttpServletRequest request) {
        return ResponseEntity.ok(customerWaitingPageService.getCustomerWaitingPageList());
    }

    @PutMapping("/{seq}")
    public ResponseEntity<CustomerWaitingPageDto> updateCustomerWaitingPage(@Valid @RequestBody CustomerWaitingPageDto customerWaitingPageDto, @PathVariable Long seq) {
        return ResponseEntity.ok(customerWaitingPageService.update(seq, customerWaitingPageDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<CustomerWaitingPageDto> deleteCustomerWaitingPage(@PathVariable Long seq) {
        return ResponseEntity.ok(customerWaitingPageService.deleteCustomerWaitingPage(seq));
    }
}
