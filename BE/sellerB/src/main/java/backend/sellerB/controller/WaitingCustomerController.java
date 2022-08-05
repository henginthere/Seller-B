package backend.sellerB.controller;

import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ProductRes;
import backend.sellerB.dto.WaitingCustomerDto;
import backend.sellerB.service.WaitingCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/waiting")
public class WaitingCustomerController {
    private final WaitingCustomerService waitingCustomerService;

    public WaitingCustomerController(WaitingCustomerService waitingCustomerService) {
        this.waitingCustomerService = waitingCustomerService;
    }

    @PostMapping
    public ResponseEntity<WaitingCustomerDto> saveWaitingCustomer(@Valid @RequestBody WaitingCustomerDto waitingCustomerDto) {
        return ResponseEntity.ok(waitingCustomerService.create(waitingCustomerDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<WaitingCustomerDto> getWaitingCustomerDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(waitingCustomerService.getWaitingCustomerDetail(seq));
    }

    @GetMapping("/list/{product-group-name}")
    public ResponseEntity<List<WaitingCustomerDto>> getWaitingCustomerListByProductGroupNameList(@PathVariable("product-group-name") String productGroupName, HttpServletRequest request) throws UnsupportedEncodingException {
        String koreanProductGroupName = URLDecoder.decode(productGroupName, "UTF-8");
        System.out.println(koreanProductGroupName);
        return ResponseEntity.ok(waitingCustomerService.getWaitingCustomersByProductGroup_ProductGroupName(koreanProductGroupName));
    }

//    @PutMapping("/{seq}")
//    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Long seq) {
//        return ResponseEntity.ok(productService.update(seq, productDto));
//    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<WaitingCustomerDto> deleteWaitingCustomer(@PathVariable Long seq) {
        return ResponseEntity.ok(waitingCustomerService.deleteWaitingCustomer(seq));
    }
}
