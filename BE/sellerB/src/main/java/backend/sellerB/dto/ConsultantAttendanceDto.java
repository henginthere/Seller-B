package backend.sellerB.dto;

import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.ConsultantAttendance;
import backend.sellerB.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ConsultantAttendanceDto {
    private Long consultantSeq;
    private Boolean consultantAttendanceState;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;

    public static ConsultantAttendanceDto from(ConsultantAttendance consultantAttendance) {
        if(consultantAttendance == null) return null;
        return ConsultantAttendanceDto.builder()
                .consultantSeq(consultantAttendance.getConsultant().getConsultantSeq())
                .consultantAttendanceState(consultantAttendance.getConsultantAttendanceState())
                .loginTime(consultantAttendance.getLoginTime())
                .logoutTime(consultantAttendance.getLogoutTime())
                .build();
    }


    public static ArrayList<ConsultantAttendanceDto> fromList(List<ConsultantAttendance> consultantAttendanceList) {
        ArrayList<ConsultantAttendanceDto> listConsultantAttendanceDto = new ArrayList<>();
        int i = 0;
        while (i < consultantAttendanceList.size()) {
            ConsultantAttendanceDto consultantAttendanceDto = ConsultantAttendanceDto.builder()
                    .consultantSeq(consultantAttendanceList.get(i).getConsultant().getConsultantSeq())
                    .consultantAttendanceState(consultantAttendanceList.get(i).getConsultantAttendanceState())
                    .loginTime(consultantAttendanceList.get(i).getLoginTime())
                    .logoutTime(consultantAttendanceList.get(i).getLogoutTime())
                    .build();
            listConsultantAttendanceDto.add(consultantAttendanceDto);
            i++;
        }
        return listConsultantAttendanceDto;
    }
}
