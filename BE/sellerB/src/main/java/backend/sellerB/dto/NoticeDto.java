package backend.sellerB.dto;

import backend.sellerB.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class NoticeDto {

    private Long noticeSeq;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime noticeRegDate;
    private LocalDateTime noticeModDate;

    //엔티티를 dto로
    public static NoticeDto from(Notice notice) {
        if(notice == null) return null;
        return NoticeDto.builder()
                .noticeSeq(notice.getNoticeSeq())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .noticeRegDate(notice.getNoticeRegDate())
                .noticeModDate(notice.getNoticeModDate())
                .build();
    }

    public static ArrayList<NoticeDto> fromList(List<Notice> noticeList) {
        ArrayList<NoticeDto> listNoticeDto= new ArrayList<>();
        int i = 0;
        while(i < noticeList.size()){
            NoticeDto noticeDto = NoticeDto.builder()
                    .noticeSeq(noticeList.get(i).getNoticeSeq())
                    .noticeTitle(noticeList.get(i).getNoticeTitle())
                    .noticeContent(noticeList.get(i).getNoticeContent())
                    .noticeRegDate(noticeList.get(i).getNoticeRegDate())
                    .noticeModDate(noticeList.get(i).getNoticeModDate())
                    .build();
            listNoticeDto.add(noticeDto);
            i++;
        }
        return listNoticeDto;
    }

}
