package backend.sellerB.controller;


import backend.sellerB.dto.*;
import backend.sellerB.service.ConsultantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
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
    public ResponseEntity<ConsultantDto> registerConsultant(@Valid @RequestBody RegisterConsultantDto registerConsultantDto) throws IOException {
        return ResponseEntity.ok(consultantService.signup(registerConsultantDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseConsultantDto>> getConsultantList(HttpServletRequest request) {
        return ResponseEntity.ok(consultantService.getConsultantList());
    }

    @GetMapping("/{consultantSeq}")
    public ResponseEntity<ResponseConsultantDto> getConsultantDetail(@PathVariable Long consultantSeq) {
        return ResponseEntity.ok(consultantService.getConsultantDetail(consultantSeq));
    }

    @GetMapping("/search/{consultantName}")
    public ResponseEntity<List<ResponseConsultantDto>> findByConsultantName(@PathVariable String consultantName) {
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


    @PutMapping("/{consultantSeq}")
    public ResponseEntity<ConsultantDto> updateConsultantInfo(@Valid @RequestBody EditConsultantDto editConsultantDto, @PathVariable Long consultantSeq) throws IOException {
        return ResponseEntity.ok(consultantService.update(editConsultantDto, consultantSeq));
    }

    @DeleteMapping("/{consultantSeq}")
    public ResponseEntity<ConsultantDto> deleteConsultant(@PathVariable Long consultantSeq) {
        return ResponseEntity.ok(consultantService.delete(consultantSeq));
    }
}
