package backend.sellerB.controller;


import backend.sellerB.dto.ConsultantDto;
import backend.sellerB.dto.EditConsultantDto;
import backend.sellerB.dto.ManagerDto;
import backend.sellerB.dto.NoticeDto;
import backend.sellerB.entity.Consultant;
import backend.sellerB.service.BrandService;
import backend.sellerB.service.ConsultantService;
import backend.sellerB.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/consultant")
public class ConsultantController {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantController.class);

    private final ConsultantService consultantService;
    public ConsultantController(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }



    @PostMapping("/register")
    public ResponseEntity<ConsultantDto> registerConsultant(@Valid @RequestBody ConsultantDto consultantDto) {
        return ResponseEntity.ok(consultantService.signup(consultantDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ConsultantDto>> getConsultantList(HttpServletRequest request) {
        return ResponseEntity.ok(consultantService.getConsultantList());
    }

    @GetMapping("/{consultantId}")
    public ResponseEntity<ConsultantDto> getConsultantDetail(@PathVariable String consultantId) {
        return ResponseEntity.ok(consultantService.getConsultantDetail(consultantId));
    }

    @GetMapping("/search/{consultantName}")
    public ResponseEntity<List<ConsultantDto>> findByConsultantName(@PathVariable String consultantName) {
        return ResponseEntity.ok(consultantService.searchByConsultantNameContaining(consultantName));
    }

//    @GetMapping("/search/{consultantId}")
//    public ResponseEntity<List<ConsultantDto>> findByConsultantId(@PathVariable String consultantId) {
//        return ResponseEntity.ok(consultantService.searchByConsultantId(consultantId));
//    }

    @GetMapping("/list/{productGroupSeq}")
    public ResponseEntity<List<ConsultantDto>> findByProductGroupSeq(@PathVariable Integer productGroupSeq) {
        return ResponseEntity.ok(consultantService.searchByProductGroupSeq(productGroupSeq));
    }

    @PutMapping("/{consultantSeq}")
    public ResponseEntity<ConsultantDto> updateConsultantInfo(@Valid @RequestBody EditConsultantDto editConsultantDto, @PathVariable Integer consultantSeq) {
        return ResponseEntity.ok(consultantService.update(editConsultantDto, consultantSeq));
    }

    @DeleteMapping("/{consultantSeq}")
    public ResponseEntity<ConsultantDto> deleteConsultant(@PathVariable Integer consultantSeq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(consultantService.delete(consultantSeq));
    }
}
