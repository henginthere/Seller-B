package backend.sellerB.dto;

import backend.sellerB.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class NoticeReq {
    private Long brandSeq;
    private String noticeTitle;
    private String noticeContent;

    public static NoticeReq from(Notice notice) {
        if(notice == null) return null;
        return NoticeReq.builder()
                .brandSeq(notice.getBrandSeq().getBrandSeq())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .build();
    }

    public static ArrayList<NoticeReq> fromList(List<Notice> noticeList) {
        ArrayList<NoticeReq> listNoticeReq= new ArrayList<>();
        int i = 0;
        while(i < noticeList.size()){
            NoticeReq noticeReq = NoticeReq.builder()
                    .brandSeq(noticeList.get(i).getBrandSeq().getBrandSeq())
                    .noticeTitle(noticeList.get(i).getNoticeTitle())
                    .noticeContent(noticeList.get(i).getNoticeContent())
                    .build();
            listNoticeReq.add(noticeReq);
            i++;
        }
        return listNoticeReq;
    }
}
