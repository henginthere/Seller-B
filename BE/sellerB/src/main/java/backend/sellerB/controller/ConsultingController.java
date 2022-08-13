package backend.sellerB.controller;

import backend.sellerB.dto.EditConsultingStateDto;
import backend.sellerB.dto.RegisterConsultingDto;
import backend.sellerB.dto.ResponseConsultingDto;
import backend.sellerB.service.ConsultingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consulting")
public class ConsultingController {
    private final ConsultingService consultingService;

    public ConsultingController(ConsultingService consultingService) {
        this.consultingService = consultingService;
    }

    @PostMapping
    public ResponseEntity<RegisterConsultingDto> saveConsulting(@Valid @RequestBody RegisterConsultingDto registerConsultingDto) throws Exception {
        return ResponseEntity.ok(consultingService.createConsulting(registerConsultingDto));
    }

    @GetMapping("/customer/list/{customer-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByCustomerId(@PathVariable("customer-id") String customerId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByCustomerId(customerId));
    }

    // day, week, month
    @GetMapping("/customer/day/{customer-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByCustomerIdDay(@PathVariable("customer-id") String customerId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByCustomerIdPeriod(customerId, 1));
    }

    @GetMapping("/customer/week/{customer-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByCustomerIdWeek(@PathVariable("customer-id") String customerId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByCustomerIdPeriod(customerId, 7));
    }

    @GetMapping("/customer/month/{customer-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByCustomerIdMonth(@PathVariable("customer-id") String customerId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByCustomerIdPeriod(customerId, 30));
    }

    @GetMapping("/consultant/list/{consultant-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByConsultantId(@PathVariable("consultant-id") String consultantId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByConsultantIdPeriod(consultantId, 1));
    }

    @GetMapping("/consultant/day/{consultant-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByConsultantIdDay(@PathVariable("consultant-id") String consultantId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByConsultantIdPeriod(consultantId, 7));
    }

    @GetMapping("/consultant/week/{consultant-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByConsultantIdWeek(@PathVariable("consultant-id") String consultantId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByConsultantIdPeriod(consultantId, 30));
    }

    @GetMapping("/consultant/month/{consultant-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByConsultantIdMonth(@PathVariable("consultant-id") String consultantId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByConsultantId(consultantId));
    }

    @PutMapping("/state/{seq}")
    public ResponseEntity<RegisterConsultingDto> updateConsultingState(@Valid @RequestBody EditConsultingStateDto editConsultingStateDto, @PathVariable Long seq) throws Exception {
        return ResponseEntity.ok(consultingService.updateConsultingState(seq, editConsultingStateDto));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<RegisterConsultingDto> updateConsulting(@Valid @RequestBody RegisterConsultingDto registerConsultingDto, @PathVariable Long seq) {
        return ResponseEntity.ok(consultingService.updateConsulting(seq, registerConsultingDto));
    }
}
