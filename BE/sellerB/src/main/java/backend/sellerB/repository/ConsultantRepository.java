package backend.sellerB.repository;

import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.Notice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
    @EntityGraph(attributePaths = "authorities") // EntityGraph annotation은 쿼리가 수행될때 Lazy조회가 아니고 Eager조회로 authorities 정보를 같이 가져옴
    Optional<Consultant> findByConsultantId(String consultantId);

    Consultant findByConsultantSeq(Long consultantSeq);

    List<Consultant> findByConsultantNameContaining(String consultantName);

//    List<Consultant> findByConsultantIdContaining(String consultantId);


    List<Consultant> findByProductGroup(Long productGroup);
    List<Consultant> findConsultantsByProductGroup_ProductGroupSeq(Long productGroupSeq);

    List<Consultant> findConsultantsByProductGroup_Brand_BrandNameKor(String brandname);
}
