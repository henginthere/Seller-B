package backend.sellerB.db.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_manager_consultant", schema = "sellerb", catalog = "")
public class ManagerConsultantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "manager_consultant_seq")
    private int managerConsultantSeq;
    @Basic
    @Column(name = "manager_seq")
    private int managerSeq;
    @Basic
    @Column(name = "consultant_seq")
    private int consultantSeq;

    public int getManagerConsultantSeq() {
        return managerConsultantSeq;
    }

    public void setManagerConsultantSeq(int managerConsultantSeq) {
        this.managerConsultantSeq = managerConsultantSeq;
    }

    public int getManagerSeq() {
        return managerSeq;
    }

    public void setManagerSeq(int managerSeq) {
        this.managerSeq = managerSeq;
    }

    public int getConsultantSeq() {
        return consultantSeq;
    }

    public void setConsultantSeq(int consultantSeq) {
        this.consultantSeq = consultantSeq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerConsultantEntity that = (ManagerConsultantEntity) o;
        return managerConsultantSeq == that.managerConsultantSeq && managerSeq == that.managerSeq && consultantSeq == that.consultantSeq;
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerConsultantSeq, managerSeq, consultantSeq);
    }
}
