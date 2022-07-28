package backend.sellerB.dto;

import backend.sellerB.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class NoticeDto {
    private String noticeTitle;
    private String noticeContent;

    //엔티티를 dto로
    public static NoticeDto from(Notice notice) {
        if(notice == null) return null;
        return NoticeDto.builder()
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .build();
    }

    public static ArrayList<NoticeDto> fromList(List<Notice> noticeList) {
        ArrayList<NoticeDto> listNoticeDto= new ArrayList<>();
        int i = 0;
        while(i < noticeList.size()){
            NoticeDto noticeDto = NoticeDto.builder()
                    .noticeTitle(noticeList.get(i).getNoticeTitle())
                    .noticeContent(noticeList.get(i).getNoticeContent())
                    .build();
            listNoticeDto.add(noticeDto);
            i++;
        }
        return listNoticeDto;
    }



//    public Notice toEntity(){
//        return new Notice();
//    }
}
