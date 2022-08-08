package backend.sellerB.controller;


import backend.sellerB.dto.*;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    public ResponseEntity<ConsultantDto> registerConsultant(@Valid @RequestBody RegisterConsultantDto registerConsultantDto) {
        return ResponseEntity.ok(consultantService.signup(registerConsultantDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseConsultantDto>> getConsultantList(HttpServletRequest request) {
        return ResponseEntity.ok(consultantService.getConsultantList());
    }

    @GetMapping("/{consultantSeq}")
    public ResponseEntity<ConsultantDto> getConsultantDetail(@PathVariable Long consultantSeq) {
        return ResponseEntity.ok(consultantService.getConsultantDetail(consultantSeq));
    }

    @GetMapping("/search/{consultantName}")
    public ResponseEntity<List<ConsultantDto>> findByConsultantName(@PathVariable String consultantName) {
        return ResponseEntity.ok(consultantService.searchByConsultantNameContaining(consultantName));
    }


    @GetMapping("/list/{productGroupSeq}")
    public ResponseEntity<List<ResponseConsultantDto>> findByProductGroupSeq(@PathVariable Long productGroupSeq) {
        return ResponseEntity.ok(consultantService.searchByProductGroupSeq(productGroupSeq));
    }

    @GetMapping("/list/brand/{brand-name}")
    public ResponseEntity<List<ResponseConsultantDto>> findByBrandName(@PathVariable("brand-name") String brandName) throws UnsupportedEncodingException {
        String koreanbrandName = URLDecoder.decode(brandName, "UTF-8");
        return ResponseEntity.ok(consultantService.searchByBrandName(koreanbrandName));
    }

//    @GetMapping("/list/brand/{brandName}")
//    public ResponseEntity<List<ConsultantDto>> findByBrandName(@PathVariable Long brandName) {
//        return ResponseEntity.ok(consultantService.searchByProductGroupSeq(productGroupSeq));
//    }

    @PutMapping("/{consultantSeq}")
    public ResponseEntity<ConsultantDto> updateConsultantInfo(@Valid @RequestBody EditConsultantDto editConsultantDto, @PathVariable Long consultantSeq) {
        return ResponseEntity.ok(consultantService.update(editConsultantDto, consultantSeq));
    }

    @DeleteMapping("/{consultantSeq}")
    public ResponseEntity<ConsultantDto> deleteConsultant(@PathVariable Long consultantSeq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(consultantService.delete(consultantSeq));
    }
}
