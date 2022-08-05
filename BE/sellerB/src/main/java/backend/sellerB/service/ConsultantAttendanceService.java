package backend.sellerB.service;

import backend.sellerB.dto.ConsultantAttendanceDto;
import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ProductRes;
import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.ConsultantAttendance;
import backend.sellerB.entity.Product;
import backend.sellerB.entity.ProductGroup;
import backend.sellerB.repository.ConsultantAttendanceRepository;
import backend.sellerB.repository.ConsultantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultantAttendanceService {
    private final ConsultantAttendanceRepository consultantAttendanceRepository;
    private final ConsultantRepository consultantRepository;

    public ConsultantAttendanceDto create(ConsultantAttendanceDto consultantAttendanceDto) {
        Optional<Consultant> consultantOptional = consultantRepository.findByConsultantId(consultantAttendanceDto.getConsultantId());
        Consultant consultant = consultantOptional.get();
        ConsultantAttendance consultantAttendance = ConsultantAttendance.builder()
                .consultant(consultant)
                .consultantAttendanceState(consultantAttendanceDto.getConsultantAttendanceState())
                .build();

        return ConsultantAttendanceDto.from(consultantAttendanceRepository.save(consultantAttendance));
    }

    public List<ConsultantAttendanceDto> getConsultantAttendanceList() {
        return ConsultantAttendanceDto.fromList(consultantAttendanceRepository.findAll());}

    public List<ConsultantAttendanceDto> getConsultantAttendanceListByConsultantId(String consultantId) {
        Optional<Consultant> consultantOptional = consultantRepository.findByConsultantId(consultantId);
        Consultant consultant = consultantOptional.get();
        return ConsultantAttendanceDto.fromList(consultantAttendanceRepository.findConsultantAttendancesByConsultant_ConsultantId(consultantId));}

    // setConsultant 값은 변경될 일 없으니 조회한거 그대로 적용
    public ConsultantAttendanceDto updateConsultantAttendance(Long seq, ConsultantAttendanceDto consultantAttendanceDto) {
        Optional<ConsultantAttendance> consultantAttendanceOptional = consultantAttendanceRepository.findById(seq);
        ConsultantAttendance consultantAttendance = consultantAttendanceOptional.get();
        consultantAttendance.setConsultant(consultantAttendance.getConsultant());
        consultantAttendance.setConsultantAttendanceState(consultantAttendanceDto.getConsultantAttendanceState());
        return ConsultantAttendanceDto.from(consultantAttendance);
    }

//    public ConsultantAttendanceDto deleteConsultantAttendance(Long seq) {
//        Optional<ConsultantAttendance> consultantAttendanceOptional = consultantAttendanceRepository.findById(seq);
//        ConsultantAttendance consultantAttendance = consultantAttendanceOptional.get();
//        consultantAttendance.se(true);
//        return ConsultantAttendanceDto.from(consultantAttendance);
//    }
}
