package backend.sellerB.repository;

import backend.sellerB.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByNoticeTitleContaining(String noticeTitle);
    List<Notice> findNoticesByBrandSeq_BrandSeq(Long brandSeq);

}
