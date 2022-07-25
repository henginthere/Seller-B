package backend.sellerB.db.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_customer", schema = "sellerb", catalog = "")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_seq")
    private int customerSeq;
    @Basic
    @Column(nullable = false, name = "customer_id")
    private String customerId;
    @Basic
    @Column(nullable = false, name = "customer_name")
    private String customerName;
    @Basic
    @Column(nullable = false, name = "customer_pass")
    private String customerPass;
    @Basic
    @Column(nullable = false, name = "customer_email")
    private String customerEmail;
    @Basic
    @Column(nullable = false, name = "customer_gender")
    private String customerGender;
    @Basic
    @Column(name = "customer_tel")
    private String customerTel;
    @Basic
    @Column(name = "customer_addr")
    private String customerAddr;
    @Basic
    @Column(name = "customer_birth")
    @NotNull
    private Timestamp customerBirth;
    @Basic
    @Column(name = "customer_token")
    private String customerToken;
    @Basic
    @Column(name = "customer_del_yn")
    private Byte customerDelYn;
    @Basic
    @Column(name = "customer_reg_user_seq")
    private Integer customerRegUserSeq;
    @Basic
    @Column(name = "customer_reg_date")
    private Timestamp customerRegDate;
    @Basic
    @Column(name = "customer_mod_user_seq")
    private Integer customerModUserSeq;
    @Basic
    @Column(name = "customer_mod_date")
    private Timestamp customerModDate;


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
