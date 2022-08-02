package backend.sellerB.controller;

import backend.sellerB.dto.AddressDto;
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

//    @Operation(summary = "get posts", description = "지역에 대한 posts들 가져오기")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostsResponseDto.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
//    @Parameters({
//            @Parameter(name = "province", description = "시", example = "경기도"),
//            @Parameter(name = "city", description = "도", example = "고양시"),
//            @Parameter(name = "hashtag", description = "검색한 해시태그", example = "['#자장면', '#중국집']")
//    })
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
