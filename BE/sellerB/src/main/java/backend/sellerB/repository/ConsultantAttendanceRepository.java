package backend.sellerB.repository;

import backend.sellerB.entity.ConsultantAttendance;
import backend.sellerB.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultantAttendanceRepository extends JpaRepository<ConsultantAttendance, Long> {
    List<ConsultantAttendance> findConsultantAttendancesByConsultant_ConsultantSeq(Long consultantSeq);
    ConsultantAttendance findConsultantAttendanceByConsultant_ConsultantSeqAndConsultantAttendanceState(Long consultantSeq, Boolean state);
}
