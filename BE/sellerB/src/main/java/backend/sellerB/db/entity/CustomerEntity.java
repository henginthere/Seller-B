package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_customer", schema = "sellerb", catalog = "")
public class CustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_seq")
    private int customerSeq;
    @Basic
    @Column(name = "customer_id")
    private String customerId;
    @Basic
    @Column(name = "customer_name")
    private String customerName;
    @Basic
    @Column(name = "customer_pass")
    private String customerPass;
    @Basic
    @Column(name = "customer_email")
    private String customerEmail;
    @Basic
    @Column(name = "customer_tel")
    private String customerTel;
    @Basic
    @Column(name = "customer_addr")
    private String customerAddr;
    @Basic
    @Column(name = "customer_birth")
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

    public int getCustomerSeq() {
        return customerSeq;
    }

    public void setCustomerSeq(int customerSeq) {
        this.customerSeq = customerSeq;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPass() {
        return customerPass;
    }

    public void setCustomerPass(String customerPass) {
        this.customerPass = customerPass;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCustomerAddr() {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }

    public Timestamp getCustomerBirth() {
        return customerBirth;
    }

    public void setCustomerBirth(Timestamp customerBirth) {
        this.customerBirth = customerBirth;
    }

    public String getCustomerToken() {
        return customerToken;
    }

    public void setCustomerToken(String customerToken) {
        this.customerToken = customerToken;
    }

    public Byte getCustomerDelYn() {
        return customerDelYn;
    }

    public void setCustomerDelYn(Byte customerDelYn) {
        this.customerDelYn = customerDelYn;
    }

    public Integer getCustomerRegUserSeq() {
        return customerRegUserSeq;
    }

    public void setCustomerRegUserSeq(Integer customerRegUserSeq) {
        this.customerRegUserSeq = customerRegUserSeq;
    }

    public Timestamp getCustomerRegDate() {
        return customerRegDate;
    }

    public void setCustomerRegDate(Timestamp customerRegDate) {
        this.customerRegDate = customerRegDate;
    }

    public Integer getCustomerModUserSeq() {
        return customerModUserSeq;
    }

    public void setCustomerModUserSeq(Integer customerModUserSeq) {
        this.customerModUserSeq = customerModUserSeq;
    }

    public Timestamp getCustomerModDate() {
        return customerModDate;
    }

    public void setCustomerModDate(Timestamp customerModDate) {
        this.customerModDate = customerModDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return customerSeq == that.customerSeq && Objects.equals(customerId, that.customerId) && Objects.equals(customerName, that.customerName) && Objects.equals(customerPass, that.customerPass) && Objects.equals(customerEmail, that.customerEmail) && Objects.equals(customerTel, that.customerTel) && Objects.equals(customerAddr, that.customerAddr) && Objects.equals(customerBirth, that.customerBirth) && Objects.equals(customerToken, that.customerToken) && Objects.equals(customerDelYn, that.customerDelYn) && Objects.equals(customerRegUserSeq, that.customerRegUserSeq) && Objects.equals(customerRegDate, that.customerRegDate) && Objects.equals(customerModUserSeq, that.customerModUserSeq) && Objects.equals(customerModDate, that.customerModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerSeq, customerId, customerName, customerPass, customerEmail, customerTel, customerAddr, customerBirth, customerToken, customerDelYn, customerRegUserSeq, customerRegDate, customerModUserSeq, customerModDate);
    }
}
