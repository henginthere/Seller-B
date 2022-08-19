package backend.sellerB.service;

import backend.sellerB.dto.ConsultantAttendanceDto;
import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.ConsultantAttendance;
import backend.sellerB.repository.ConsultantAttendanceRepository;
import backend.sellerB.repository.ConsultantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ConsultantAttendanceService {
    private final ConsultantAttendanceRepository consultantAttendanceRepository;
    private final ConsultantRepository consultantRepository;

    public ConsultantAttendanceDto create(ConsultantAttendanceDto consultantAttendanceDto) {
        Optional<ConsultantAttendance> check
                = consultantAttendanceRepository.findConsultantAttendanceByConsultant_ConsultantSeqAndConsultantAttendanceState(consultantAttendanceDto.getConsultantSeq(),false);
        Consultant consultant = consultantRepository.findByConsultantSeq(consultantAttendanceDto.getConsultantSeq());
        //check가 있으면 퇴근을 안눌렀다는 것인데, 날짜가 달라지면 즉 다음날이 되면 새로 컬럼 생성 되어야함
        if(check.isPresent()){
            if(check.get().getLoginTime().getDayOfYear()!=LocalDateTime.now().getDayOfYear()){
                check.get().setConsultantAttendanceState(true);
                ConsultantAttendance consultantAttendance = ConsultantAttendance.builder()
                        .consultant(consultant)
                        .build();
                return ConsultantAttendanceDto.from(consultantAttendanceRepository.save(consultantAttendance));
            }
            return null;
        }

        ConsultantAttendance consultantAttendance = ConsultantAttendance.builder()
                .consultant(consultant)
                .build();
        return ConsultantAttendanceDto.from(consultantAttendanceRepository.save(consultantAttendance));
    }

    public List<ConsultantAttendanceDto> getConsultantAttendanceList() {
        return ConsultantAttendanceDto.fromList(consultantAttendanceRepository.findAll());}

    public List<ConsultantAttendanceDto> getConsultantAttendanceListByConsultantSeq(Long consultantSeq) {
        Consultant consultant = consultantRepository.findByConsultantSeq(consultantSeq);
        return ConsultantAttendanceDto.fromList(consultantAttendanceRepository.findConsultantAttendancesByConsultant_ConsultantSeq(consultantSeq));}

    // setConsultant 값은 변경될 일 없으니 조회한거 그대로 적용
    public ConsultantAttendanceDto updateConsultantAttendance(Long seq) {


        List<ConsultantAttendance> consultantAttendance
                = consultantAttendanceRepository.findConsultantAttendancesByConsultant_ConsultantSeq(seq);
       // ConsultantAttendance consultantAttendance = consultantAttendanceOptional.get();
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        for(int i=consultantAttendance.size()-1;i>-1;i--){
            LocalDateTime today = consultantAttendance.get(i).getLoginTime().truncatedTo(ChronoUnit.DAYS);
            int compareResult = today.compareTo(now);
            if(compareResult==0){
                consultantAttendance.get(i).setConsultant(consultantAttendance.get(i).getConsultant());
                consultantAttendance.get(i).setConsultantAttendanceState(true);
                consultantAttendance.get(i).setLogoutTime(LocalDateTime.now());
                return ConsultantAttendanceDto.from(consultantAttendanceRepository.save(consultantAttendance.get(i)));
            }
        }

        return null; // 출근정보가 없습니다 경고

    }

}
