package backend.sellerB.service;

import backend.sellerB.dto.NoticeDto;
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
    public NoticeDto create(NoticeDto noticeDto) {

        //dto를 엔티티로
        Notice notice = Notice.builder()
                .noticeTitle(noticeDto.getNoticeTitle())
                .noticeContent(noticeDto.getNoticeContent())
                .build();

        return NoticeDto.from(noticeRepository.save(notice));

    }

    public List<NoticeDto> getNoticeList() {
        return NoticeDto.fromList(noticeRepository.findAll());
    }

    public NoticeDto getNoticeDetail(Integer seq) {
//        Notice notice = noticeRepository.findById(seq).orElseThrow();
        Optional<Notice> noticeOptional = noticeRepository.findById(seq);
        Notice notice = noticeOptional.get();
        return NoticeDto.from(notice);
    }

    public NoticeDto update(Integer seq, NoticeDto noticeDto) {
        Optional<Notice> noticeOptional = noticeRepository.findById(seq);
        Notice notice = noticeOptional.get();
        notice.setNoticeTitle(noticeDto.getNoticeTitle());
        notice.setNoticeContent(noticeDto.getNoticeContent());
        return NoticeDto.from(notice);
    }

    public Integer delete(Integer seq) {
        Optional<Notice> noticeOptional = noticeRepository.findById(seq);
        if(!noticeOptional.isPresent()) throw new EntityNotFoundException();
        try {
            Notice deleteNotice = noticeOptional.get();
            noticeRepository.delete(deleteNotice);
        } catch (Exception e){
            e.printStackTrace();
        }
        return seq;


    }
    public NoticeDto deleteNotice(Integer seq) {
        Optional<Notice> noticeOptional = noticeRepository.findById(seq);
        Notice notice = noticeOptional.get();
        notice.setNoticeDelYn("Y");
        return NoticeDto.from(notice);
    }
}
