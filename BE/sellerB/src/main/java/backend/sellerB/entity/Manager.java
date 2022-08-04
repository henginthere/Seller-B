package backend.sellerB.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_manager", schema = "sellerb", catalog = "")
public class Manager implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "manager_seq")
    private Long managerSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_seq")
    private Brand brandSeq;
    @Basic
    @Column(name = "manager_id")
    private String managerId;
    @Basic
    @Column(name = "manager_name")
    private String managerName;
    @Basic
    @Column(name = "manager_pass")
    private String managerPass;
    @Basic
    @Column(name = "manager_tel")
    private String managerTel;
    @Basic
    @Column(name = "manager_email")
    private String managerEmail;
    @Basic
    @Column(name = "manager_image_url")
    private String managerImageUrl;
    @Basic
    @Column(name = "manager_del_yn",columnDefinition = "boolean default false")
    private Boolean managerDelYn;
    @Basic
    @Column(name = "manager_reg_user_seq")
    private Integer managerRegUserSeq;
    @Basic
    @Column(name = "manager_reg_date")
    private Timestamp managerRegDate;
    @Basic
    @Column(name = "manager_mod_user_seq")
    private Integer managerModUserSeq;
    @Basic
    @Column(name = "manager_mod_date")
    private Timestamp managerModDate;



    public <T> Manager(String valueOf, String managerPass, Set<T> singleton) {
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager that = (Manager) o;
        return managerSeq == that.managerSeq && brandSeq == that.brandSeq && Objects.equals(managerId, that.managerId) && Objects.equals(managerName, that.managerName) && Objects.equals(managerImageUrl, that.managerImageUrl) && Objects.equals(managerPass, that.managerPass) && Objects.equals(managerTel, that.managerTel) && Objects.equals(managerEmail, that.managerEmail) && Objects.equals(managerDelYn, that.managerDelYn) && Objects.equals(managerRegUserSeq, that.managerRegUserSeq) && Objects.equals(managerRegDate, that.managerRegDate) && Objects.equals(managerModUserSeq, that.managerModUserSeq) && Objects.equals(managerModDate, that.managerModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerSeq, brandSeq, managerId, managerName, managerPass, managerTel, managerEmail, managerImageUrl, managerDelYn, managerRegUserSeq, managerRegDate, managerModUserSeq, managerModDate);
    }


    @ManyToMany // user와 authority 다대다 관계를 일대다, 다대일 관계의 조인테이블로 정의
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "manager_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;


}
