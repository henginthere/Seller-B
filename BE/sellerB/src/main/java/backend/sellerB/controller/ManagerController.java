package backend.sellerB.controller;

import backend.sellerB.dto.ManagerDto;
import backend.sellerB.dto.NoticeDto;
import backend.sellerB.service.AuthService;
import backend.sellerB.service.ManagerService;
import backend.sellerB.service.NoticeService;
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
@RequestMapping("/manager")
public class ManagerController {
    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    private final ManagerService managerService;


    @PostMapping("/register")
    public ResponseEntity<ManagerDto> registerManager(@Valid @RequestBody ManagerDto managerDto) {
        return ResponseEntity.ok(managerService.signup(managerDto));
    }


}
