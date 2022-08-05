package backend.sellerB.service;

import backend.sellerB.dto.NoticeDto;
import backend.sellerB.dto.NoticeReq;
import backend.sellerB.entity.Notice;
import backend.sellerB.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public NoticeReq create(NoticeReq noticeReq) {

        //dto를 엔티티로
        Notice notice = Notice.builder()
                .noticeTitle(noticeReq.getNoticeTitle())
                .noticeContent(noticeReq.getNoticeContent())
                .build();

        return NoticeReq.from(noticeRepository.save(notice));

    }

    public List<NoticeDto> getNoticeList() {
        return NoticeDto.fromList(noticeRepository.findAll());
    }

    public NoticeDto getNoticeDetail(Long seq) {
//        Notice notice = noticeRepository.findById(seq).orElseThrow();
        Optional<Notice> noticeOptional = noticeRepository.findById(seq);
        Notice notice = noticeOptional.get();
        return NoticeDto.from(notice);
    }



    public NoticeReq update(Long seq, NoticeReq noticeReq) {
        Optional<Notice> noticeOptional = noticeRepository.findById(seq);
        Notice notice = noticeOptional.get();
        notice.setNoticeTitle(noticeReq.getNoticeTitle());
        notice.setNoticeContent(noticeReq.getNoticeContent());
        return NoticeReq.from(notice);
    }

//    public Long delete(Long seq) {
//        Optional<Notice> noticeOptional = noticeRepository.findById(seq);
//        if(!noticeOptional.isPresent()) throw new EntityNotFoundException();
//        try {
//            Notice deleteNotice = noticeOptional.get();
//            noticeRepository.delete(deleteNotice);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return seq;
//
//
//    }
    public NoticeDto deleteNotice(Long seq) {
        Optional<Notice> noticeOptional = noticeRepository.findById(seq);
        Notice notice = noticeOptional.get();
        notice.setNoticeDelYn(true);
        return NoticeDto.from(notice);
    }

    public List<NoticeDto> search(String noticeTitle) {

       return NoticeDto.fromList(noticeRepository.findByNoticeTitleContaining(noticeTitle));
    }

}
