package backend.sellerB.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "t_consultant", schema = "sellerb", catalog = "")
public class Consultant implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consultant_seq")
    private int consultantSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group_seq")
    private ProductGroup productGroup;
    @Basic
    @Column(name = "consultant_pass")
    private String consultantPass;
    @Basic
    @Column(name = "consultant_id")
    private String consultantId;

    @Basic
    @Column(name = "consultant_name")
    private String consultantName;
    @Basic
    @Column(name = "consultant_image_url")
    private String consultantImageUrl;
    @Basic
    @Column(name = "consultant_tel")
    private String consultantTel;
    @Basic
    @Column(name = "consultant_email")
    private String consultantEmail;
    @Basic
    @Column(name = "consultant_del_yn",columnDefinition = "boolean default false")
    private Boolean consultantDelYn;
    @Basic
    @Column(name = "consultant_reg_user_seq")
    private Integer consultantRegUserSeq;
    @Basic
    @Column(name = "consultant_reg_date")
    private Timestamp consultantRegDate;
    @Basic
    @Column(name = "consultant_mod_user_seq")
    private Integer consultantModUserSeq;
    @Basic
    @Column(name = "consultant_mod_date")
    private Timestamp consultantModDate;


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
