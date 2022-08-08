package backend.sellerB.controller;

import backend.sellerB.dto.RegisterConsultingDto;
import backend.sellerB.dto.ResponseConsultingDto;
import backend.sellerB.service.ConsultingService;
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
    public ResponseEntity<RegisterConsultingDto> saveConsulting(@Valid @RequestBody RegisterConsultingDto registerConsultingDto) {
        return ResponseEntity.ok(consultingService.createConsulting(registerConsultingDto));
    }

    @GetMapping("/customer/{customer-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByCustomerId(@PathVariable("customer-id") String customerId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByCustomerId(customerId));
    }

    @GetMapping("/consultant/{consultant-id}")
    public ResponseEntity<List<ResponseConsultingDto>> getConsultingByConsultantId(@PathVariable("consultant-id") String consultantId, HttpServletRequest request) {
        return ResponseEntity.ok(consultingService.getConsultingListByConsultantId(consultantId));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<RegisterConsultingDto> updateConsulting(@Valid @RequestBody RegisterConsultingDto registerConsultingDto, @PathVariable Long seq) {
        return ResponseEntity.ok(consultingService.updateConsulting(seq, registerConsultingDto));
    }
}
