package backend.sellerB.controller;

import backend.sellerB.dto.CreateWaitingCustomerDto;
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
    public ResponseEntity<WaitingCustomerDto> saveWaitingCustomer(@Valid @RequestBody CreateWaitingCustomerDto createWaitingCustomerDto) {
        return ResponseEntity.ok(waitingCustomerService.create(createWaitingCustomerDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<WaitingCustomerDto> getWaitingCustomerDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(waitingCustomerService.getWaitingCustomerDetail(seq));
    }

    @GetMapping("/list/{productGroupSeq}")
    public ResponseEntity<List<WaitingCustomerDto>> getWaitingCustomerListByProductGroupSeq(@PathVariable Long productGroupSeq, HttpServletRequest request){
        return ResponseEntity.ok(waitingCustomerService.getWaitingCustomersByProductGroup_ProductGroupSeq(productGroupSeq));
    }


    @DeleteMapping("/{seq}")
    public ResponseEntity<WaitingCustomerDto> deleteWaitingCustomer(@PathVariable Long seq) {
        return ResponseEntity.ok(waitingCustomerService.deleteWaitingCustomer(seq));
    }
}
