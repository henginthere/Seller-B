package backend.sellerB.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter
@Table(name = "t_manager_consultant", schema = "sellerb", catalog = "")
public class ManagerConsultant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "manager_consultant_seq")
    private int managerConsultantSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_seq")
    private Manager manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_seq")
    private Consultant consultant;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerConsultant that = (ManagerConsultant) o;
        return managerConsultantSeq == that.managerConsultantSeq && manager == that.manager && consultant == that.consultant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerConsultantSeq, manager, consultant);
    }
}