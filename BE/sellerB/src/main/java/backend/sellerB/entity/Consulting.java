package backend.sellerB.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_consulting", schema = "sellerb", catalog = "")
public class Consulting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consulting_seq")
    private Long consultingSeq;
    @ManyToOne
    @JoinColumn(name = "customer_seq")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "consultant_seq")
    private Consultant consultant;
    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;

    @CreatedBy
    @Basic
    @Column(name = "consulting_reg_user")
    private String consultingRegUser;
    //컬럼 생성 시간은 상담 시작 시간
    @CreatedDate
    @Basic
    @Column(name = "consulting_start_date")
    private LocalDateTime consultingStartDate;

    @LastModifiedBy
    @Basic
    @Column(name = "consulting_mod_user")
    private String consultingModUser;

    //컬럼 수정 시간은 상담 종료 시간 (consultingState 수정할 것이므로)
    @LastModifiedDate
    @Basic
    @Column(name = "consulting_end_date")
    private LocalDateTime consultingEndDate;

    // 생성 시 waiting -> 고객 입장 시 start -> 고객 퇴장 시 delete
    @Basic
    @Column(name = "consulting_state",columnDefinition = "string default 'waiting'")
    private String consultingState;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulting that = (Consulting) o;
        return consultingSeq == that.consultingSeq && customer == that.customer && consultant == that.consultant && product == that.product && Objects.equals(consultingStartDate, that.consultingStartDate) && Objects.equals(consultingEndDate, that.consultingEndDate) && Objects.equals(consultingState, that.consultingState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultingSeq, customer, consultant, product, consultingStartDate, consultingEndDate, consultingState);
    }
}
