package backend.sellerB.repository;

import backend.sellerB.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

}
