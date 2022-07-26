package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_manager", schema = "sellerb", catalog = "")
public class ManagerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "manager_seq")
    private int managerSeq;
    @Basic
    @Column(name = "brand_seq")
    private int brandSeq;
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
    @Column(name = "manager_del_yn")
    private Byte managerDelYn;
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

    public int getManagerSeq() {
        return managerSeq;
    }

    public void setManagerSeq(int managerSeq) {
        this.managerSeq = managerSeq;
    }

    public int getBrandSeq() {
        return brandSeq;
    }

    public void setBrandSeq(int brandSeq) {
        this.brandSeq = brandSeq;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPass() {
        return managerPass;
    }

    public void setManagerPass(String managerPass) {
        this.managerPass = managerPass;
    }

    public String getManagerTel() {
        return managerTel;
    }

    public void setManagerTel(String managerTel) {
        this.managerTel = managerTel;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public Byte getManagerDelYn() {
        return managerDelYn;
    }

    public void setManagerDelYn(Byte managerDelYn) {
        this.managerDelYn = managerDelYn;
    }

    public Integer getManagerRegUserSeq() {
        return managerRegUserSeq;
    }

    public void setManagerRegUserSeq(Integer managerRegUserSeq) {
        this.managerRegUserSeq = managerRegUserSeq;
    }

    public Timestamp getManagerRegDate() {
        return managerRegDate;
    }

    public void setManagerRegDate(Timestamp managerRegDate) {
        this.managerRegDate = managerRegDate;
    }

    public Integer getManagerModUserSeq() {
        return managerModUserSeq;
    }

    public void setManagerModUserSeq(Integer managerModUserSeq) {
        this.managerModUserSeq = managerModUserSeq;
    }

    public Timestamp getManagerModDate() {
        return managerModDate;
    }

    public void setManagerModDate(Timestamp managerModDate) {
        this.managerModDate = managerModDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerEntity that = (ManagerEntity) o;
        return managerSeq == that.managerSeq && brandSeq == that.brandSeq && Objects.equals(managerId, that.managerId) && Objects.equals(managerName, that.managerName) && Objects.equals(managerPass, that.managerPass) && Objects.equals(managerTel, that.managerTel) && Objects.equals(managerEmail, that.managerEmail) && Objects.equals(managerDelYn, that.managerDelYn) && Objects.equals(managerRegUserSeq, that.managerRegUserSeq) && Objects.equals(managerRegDate, that.managerRegDate) && Objects.equals(managerModUserSeq, that.managerModUserSeq) && Objects.equals(managerModDate, that.managerModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerSeq, brandSeq, managerId, managerName, managerPass, managerTel, managerEmail, managerDelYn, managerRegUserSeq, managerRegDate, managerModUserSeq, managerModDate);
    }
}
