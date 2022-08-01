package backend.sellerB.controller;

import backend.sellerB.dto.ManagerDto;
import backend.sellerB.dto.NoticeDto;
import backend.sellerB.service.ManagerService;
import backend.sellerB.service.NoticeService;
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

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    private final ManagerService managerService;

    @PostMapping
    public ResponseEntity<ManagerDto> registerManager(@Valid @RequestBody ManagerDto managerDto) {
        return ResponseEntity.ok(managerService.signup(managerDto));
    }


}
