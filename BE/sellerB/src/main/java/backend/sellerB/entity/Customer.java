package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
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
@Table(name = "t_customer", schema = "sellerb", catalog = "")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_seq")
    private Long customerSeq;
    @Basic
    @Column(name = "customer_id")
    private String customerId;
    @Basic
    @Column(name = "customer_name")
    private String customerName;
    @Basic
    @JsonIgnore
    // 쓰기 전용 및 조회 불가
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "customer_pass")
    private String customerPass;
    @Basic
    @Column(name = "customer_email")
    private String customerEmail;
    @Basic
    @Column(name = "customer_gender")
    private String customerGender;
    @Basic
    @Column(name = "customer_tel")
    private String customerTel;
    @Basic
    @Column(name = "customer_addr")
    private String customerAddr;
    @Basic
    @Column(name = "customer_birth")
    private LocalDateTime customerBirth;
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
        return customerSeq == that.customerSeq && Objects.equals(customerId, that.customerId) && Objects.equals(customerName, that.customerName) && Objects.equals(customerPass, that.customerPass)  && Objects.equals(customerGender, that.customerGender) && Objects.equals(customerEmail, that.customerEmail) && Objects.equals(customerTel, that.customerTel) && Objects.equals(customerAddr, that.customerAddr) && Objects.equals(customerBirth, that.customerBirth) && Objects.equals(customerToken, that.customerToken) && Objects.equals(customerDelYn, that.customerDelYn) && Objects.equals(customerRegUserSeq, that.customerRegUserSeq) && Objects.equals(customerRegDate, that.customerRegDate) && Objects.equals(customerModUserSeq, that.customerModUserSeq) && Objects.equals(customerModDate, that.customerModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerSeq, customerId, customerName, customerPass, customerGender, customerEmail, customerTel, customerAddr, customerBirth, customerToken, customerDelYn, customerRegUserSeq, customerRegDate, customerModUserSeq, customerModDate);
    }
}
