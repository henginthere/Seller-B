package backend.sellerB.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_manager", schema = "sellerb", catalog = "")
public class Manager {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "manager_seq")
    private int managerSeq;
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
}
