package backend.sellerB.controller;

import backend.sellerB.dto.AddressDto;
import backend.sellerB.dto.RegisterAddressDto;
import backend.sellerB.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
@Tag(name = "address", description = "배송지 API")
@RestController
@RequestMapping("/customer/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "get posts", description = "지역에 대한 posts들 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AddressDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @Parameters({
            @Parameter(name = "customer", description = "고객", example = "고객 엔티티인데 이건 param이 아니지 않나"),
            @Parameter(name = "addr", description = "배송지", example = "구미 진평동"),
            @Parameter(name = "addrRequest", description = "요청사항", example = "문 앞에 놓고 가주세요")
    })
    @PostMapping
    public ResponseEntity<RegisterAddressDto> saveAddress(@Valid @RequestBody RegisterAddressDto registerAddressDto) {
        return ResponseEntity.ok(addressService.create(registerAddressDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<AddressDto> getAddressDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(addressService.getAddressDetail(seq));
    }

    @GetMapping("/list/{customer-id}")
    public ResponseEntity<List<AddressDto>> getAddressList(@PathVariable String customerId, HttpServletRequest request) {
        return ResponseEntity.ok(addressService.getAddressList(customerId));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<RegisterAddressDto> updateAddress(@Valid @RequestBody RegisterAddressDto registerAddressDto, @PathVariable Long seq) {
        return ResponseEntity.ok(addressService.updateAddress(seq, registerAddressDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long seq) {
        return ResponseEntity.ok(addressService.deleteAddress(seq));
    }
}
