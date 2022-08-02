package backend.sellerB.controller;

import backend.sellerB.dto.AddressDto;
import backend.sellerB.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDto> saveAddress(@Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.create(addressDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<AddressDto> getAddressDetail(@PathVariable Integer seq) {
        return ResponseEntity.ok(addressService.getAddressDetail(seq));
    }

    @GetMapping("/list/{customer-id}")
    public ResponseEntity<List<AddressDto>> getAddressList(@PathVariable String customerId, HttpServletRequest request) {
        return ResponseEntity.ok(addressService.getAddressList(customerId));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<AddressDto> updateAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Integer seq) {
        return ResponseEntity.ok(addressService.updateAddress(seq, addressDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable Integer seq) {
        return ResponseEntity.ok(addressService.deleteAddress(seq));
    }
}
