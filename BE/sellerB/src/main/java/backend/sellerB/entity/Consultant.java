package backend.sellerB.entity;

import lombok.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE t_consultant SET consultant_name=null, consultant_pass=null, consultant_email=null, consultant_tel=null, consultant_image_url=null,consultant_del_yn=true WHERE consultant_seq=?")
@Where(clause = "consultant_del_yn=false")
@Table(name = "t_consultant", schema = "sellerb", catalog = "")
public class Consultant implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consultant_seq")
    private Long consultantSeq;
    @ManyToOne
    @JoinColumn(name = "product_group_seq")
    private ProductGroup productGroup;
    @Basic
    @Column(name = "consultant_pass",length = 100)
    private String consultantPass;
    @Basic
    @Column(name = "consultant_id",length = 25)
    private String consultantId;

    @Basic
    @Column(name = "consultant_name",length = 10)
    private String consultantName;
    @Basic
    @Column(name = "consultant_image_url")
    private String consultantImageUrl;
    @Basic
    @Column(name = "consultant_tel", length = 15)
    private String consultantTel;
    @Basic
    @Column(name = "consultant_email", length = 50)
    private String consultantEmail;
    @Basic
    @Column(name = "consultant_del_yn",columnDefinition = "boolean default false")
    private Boolean consultantDelYn;
    @CreatedBy
    @Basic
    @Column(name = "consultant_reg_user_seq")
    private Long consultantRegUserSeq;

    @CreatedDate
    @Basic
    @Column(name = "consultant_reg_date")
    private LocalDateTime consultantRegDate;
    @LastModifiedBy
    @Basic
    @Column(name = "consultant_mod_user_seq")
    private Long consultantModUserSeq;
    @LastModifiedDate
    @Basic
    @Column(name = "consultant_mod_date")
    private LocalDateTime consultantModDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultant that = (Consultant) o;
        return consultantSeq == that.consultantSeq && productGroup == that.productGroup && Objects.equals(consultantId, that.consultantId) && Objects.equals(consultantPass, that.consultantPass) && Objects.equals(consultantName, that.consultantName) && Objects.equals(consultantImageUrl, that.consultantImageUrl) && Objects.equals(consultantTel, that.consultantTel) && Objects.equals(consultantEmail, that.consultantEmail) && Objects.equals(consultantDelYn, that.consultantDelYn) && Objects.equals(consultantRegUserSeq, that.consultantRegUserSeq) && Objects.equals(consultantRegDate, that.consultantRegDate) && Objects.equals(consultantModUserSeq, that.consultantModUserSeq) && Objects.equals(consultantModDate, that.consultantModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultantSeq, productGroup, consultantId, consultantPass, consultantName, consultantImageUrl, consultantTel, consultantEmail, consultantDelYn, consultantRegUserSeq, consultantRegDate, consultantModUserSeq, consultantModDate);
    }

    @ManyToMany // user와 authority 다대다 관계를 일대다, 다대일 관계의 조인테이블로 정의
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "consultant_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
