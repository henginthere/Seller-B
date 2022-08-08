package backend.sellerB.controller;

import backend.sellerB.dto.*;
import backend.sellerB.service.AuthService;
import backend.sellerB.service.ManagerService;
import backend.sellerB.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ManagerDto> registerManager(@Valid @RequestBody RegisterManagerDto registerManagerDto) {
        return ResponseEntity.ok(managerService.signup(registerManagerDto));
    }

    @GetMapping("/{managerSeq}")
    public ResponseEntity<ManagerDto> getManagerDetail(@PathVariable Long managerSeq) {
        return ResponseEntity.ok(managerService.getManagerDetail(managerSeq));
    }

    @PutMapping("/{managerSeq}")
    public ResponseEntity<ManagerDto> updateManagerInfo(@Valid @RequestBody EditManagerDto editManagerDto, @PathVariable Long managerSeq) {
        return ResponseEntity.ok(managerService.update(editManagerDto, managerSeq));
    }


    @DeleteMapping("/{managerSeq}")
    public ResponseEntity<ManagerDto> deleteManager(@PathVariable Long managerSeq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(managerService.delete(managerSeq));
    }




}
