package backend.sellerB.entity;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_manager_consultant", schema = "sellerb", catalog = "")
public class ManagerConsultant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "manager_consultant_seq")
    private Long managerConsultantSeq;

    @ManyToOne
    @JoinColumn(name = "manager_seq")
    @JsonIgnore
    private Manager manager;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "consultant_seq")
    private Consultant consultant;
    @Basic
    @Column(name = "consultant_del_yn",columnDefinition = "boolean default false")
    private Boolean consultantDelYn;
    @CreatedBy
    @Basic
    @Column(name = "manager_consultant_reg_user_seq")
    private Long managerConsultantRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "manager_consultant_reg_date")
    private LocalDateTime managerConsultantRegDate;

    @LastModifiedBy
    @Basic
    @Column(name = "manager_consultant_mod_user_seq")
    private Long managerConsultantModUserSeq;

    @LastModifiedDate
    @Basic
    @Column(name = "manager_consultant_mod_date")
    private LocalDateTime managerConsultantModDate;

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
