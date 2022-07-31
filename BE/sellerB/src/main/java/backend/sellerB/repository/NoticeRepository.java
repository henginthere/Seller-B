package backend.sellerB.repository;

import backend.sellerB.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    List<Notice> findByNoticeTitleContaining(String noticeTitle);

}
