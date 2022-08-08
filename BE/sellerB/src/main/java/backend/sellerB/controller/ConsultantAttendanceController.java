package backend.sellerB.controller;

import backend.sellerB.dto.*;
import backend.sellerB.service.ConsultantAttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consultant-attendance")
public class ConsultantAttendanceController {
    private final ConsultantAttendanceService consultantAttendanceService;

    public ConsultantAttendanceController(ConsultantAttendanceService consultantAttendanceService) {
        this.consultantAttendanceService = consultantAttendanceService;
    }

    @PostMapping
    public ResponseEntity<ConsultantAttendanceDto> saveConsultantAttendance(@Valid @RequestBody ConsultantAttendanceDto consultantAttendanceDto) {
        return ResponseEntity.ok(consultantAttendanceService.create(consultantAttendanceDto));
    }


    @GetMapping("/list")
    public ResponseEntity<List<ConsultantAttendanceDto>> getConsultantAttendanceList(HttpServletRequest request) {
        return ResponseEntity.ok(consultantAttendanceService.getConsultantAttendanceList());
    }

    @GetMapping("/list/{consultantSeq}")
    public ResponseEntity<List<ConsultantAttendanceDto>> getConsultantAttendanceListByConsultantId(@PathVariable Long consultantSeq) {
        return ResponseEntity.ok(consultantAttendanceService.getConsultantAttendanceListByConsultantSeq(consultantSeq));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ConsultantAttendanceDto> updateConsultantAttendance(@PathVariable Long seq) {
        return ResponseEntity.ok(consultantAttendanceService.updateConsultantAttendance(seq));
    }

//    @DeleteMapping("/{seq}")
//    public ResponseEntity<ConsultantAttendanceDto> leaveWorkConsultant(@PathVariable Long seq) {
//        // Access the DB and delete the order
//        return ResponseEntity.ok(consultantAttendanceService.leaveWork(seq));
//    }
}
