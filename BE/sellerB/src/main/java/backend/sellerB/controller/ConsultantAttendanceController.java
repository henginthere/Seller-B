package backend.sellerB.controller;

import backend.sellerB.dto.BrandDto;
import backend.sellerB.dto.ConsultantAttendanceDto;
import backend.sellerB.dto.ResponseBrandDto;
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

    @GetMapping("/list/{consultant-id}")
    public ResponseEntity<List<ConsultantAttendanceDto>> getConsultantAttendanceListByConsultantId(HttpServletRequest request,  @PathVariable("consultant-id") String consultantId) {
        return ResponseEntity.ok(consultantAttendanceService.getConsultantAttendanceListByConsultantId(consultantId));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ConsultantAttendanceDto> updateConsultantAttendance(@Valid @RequestBody ConsultantAttendanceDto consultantAttendanceDto, @PathVariable Long seq) {
        return ResponseEntity.ok(consultantAttendanceService.updateConsultantAttendance(seq, consultantAttendanceDto));
    }
}
