package backend.sellerB.controller;


import backend.sellerB.dto.ConsultantDto;
import backend.sellerB.dto.ManagerDto;
import backend.sellerB.entity.Consultant;
import backend.sellerB.service.BrandService;
import backend.sellerB.service.ConsultantService;
import backend.sellerB.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

}
