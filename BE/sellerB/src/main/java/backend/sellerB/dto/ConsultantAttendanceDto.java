package backend.sellerB.dto;

import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.ConsultantAttendance;
import backend.sellerB.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ConsultantAttendanceDto {
    private String consultantId;
    private Boolean consultantAttendanceState;

    public static ConsultantAttendanceDto from(ConsultantAttendance consultantAttendance) {
        if(consultantAttendance == null) return null;
        return ConsultantAttendanceDto.builder()
                .consultantId(consultantAttendance.getConsultant().getConsultantId())
                .consultantAttendanceState(consultantAttendance.getConsultantAttendanceState())
                .build();
    }


    public static ArrayList<ConsultantAttendanceDto> fromList(List<ConsultantAttendance> consultantAttendanceList) {
        ArrayList<ConsultantAttendanceDto> listConsultantAttendanceDto = new ArrayList<>();
        int i = 0;
        while (i < consultantAttendanceList.size()) {
            ConsultantAttendanceDto consultantAttendanceDto = ConsultantAttendanceDto.builder()
                    .consultantId(consultantAttendanceList.get(i).getConsultant().getConsultantId())
                    .consultantAttendanceState(consultantAttendanceList.get(i).getConsultantAttendanceState())
                    .build();
            listConsultantAttendanceDto.add(consultantAttendanceDto);
            i++;
        }
        return listConsultantAttendanceDto;
    }
}
