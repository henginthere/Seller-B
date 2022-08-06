package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
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
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE t_customer SET cutomer_id=null, customer_name=null, customer_pass=null, customer_email=null, customer_birth=null, customer_token=null, customer_del_yn=true WHERE customer_seq=?")
@Where(clause = "customer_del_yn=false")
@Table(name = "t_customer", schema = "sellerb", catalog = "")
public class Customer implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_seq")
    private Long customerSeq;
    @Basic
    @Column(name = "customer_id", length = 25)
    private String customerId;
    @Basic
    @Column(name = "customer_name", length = 10)
    private String customerName;
    @Basic
    @JsonIgnore
    // 쓰기 전용 및 조회 불가
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "customer_pass", length = 100)
    private String customerPass;
    @Basic
    @Column(name = "customer_email", length = 50)
    private String customerEmail;
    @Basic
    @Column(name = "customer_birth",length = 10)
    private String customerBirth;
    @Basic
    @Column(name = "customer_token")
    private String customerToken;
    @Basic
    @Column(name = "customer_del_yn",columnDefinition = "boolean default false")
    private Boolean customerDelYn;
    @CreatedBy
    @Basic
    @Column(name = "customer_reg_user_seq")
    private Long customerRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "customer_reg_date")
    private LocalDateTime customerRegDate;
    @LastModifiedBy
    @Basic
    @Column(name = "customer_mod_user_seq")
    private Long customerModUserSeq;
    @LastModifiedDate
    @Basic
    @Column(name = "customer_mod_date")
    private LocalDateTime customerModDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return customerSeq == that.customerSeq && Objects.equals(customerId, that.customerId) && Objects.equals(customerName, that.customerName) && Objects.equals(customerPass, that.customerPass)  && Objects.equals(customerEmail, that.customerEmail) && Objects.equals(customerBirth, that.customerBirth) && Objects.equals(customerToken, that.customerToken) && Objects.equals(customerDelYn, that.customerDelYn) && Objects.equals(customerRegUserSeq, that.customerRegUserSeq) && Objects.equals(customerRegDate, that.customerRegDate) && Objects.equals(customerModUserSeq, that.customerModUserSeq) && Objects.equals(customerModDate, that.customerModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerSeq, customerId, customerName, customerPass, customerEmail, customerBirth, customerToken, customerDelYn, customerRegUserSeq, customerRegDate, customerModUserSeq, customerModDate);
    }


    @ManyToMany // user와 authority 다대다 관계를 일대다, 다대일 관계의 조인테이블로 정의
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
