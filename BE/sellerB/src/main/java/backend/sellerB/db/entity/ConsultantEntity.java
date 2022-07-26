package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_consultant", schema = "sellerb", catalog = "")
public class ConsultantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consultant_seq")
    private int consultantSeq;
    @Basic
    @Column(name = "product_group_seq")
    private int productGroupSeq;
    @Basic
    @Column(name = "consultant_id")
    private String consultantId;
    @Basic
    @Column(name = "consultant_pass")
    private String consultantPass;
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
    @Column(name = "consultant_del_yn")
    private Byte consultantDelYn;
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

    public int getConsultantSeq() {
        return consultantSeq;
    }

    public void setConsultantSeq(int consultantSeq) {
        this.consultantSeq = consultantSeq;
    }

    public int getProductGroupSeq() {
        return productGroupSeq;
    }

    public void setProductGroupSeq(int productGroupSeq) {
        this.productGroupSeq = productGroupSeq;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getConsultantPass() {
        return consultantPass;
    }

    public void setConsultantPass(String consultantPass) {
        this.consultantPass = consultantPass;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getConsultantImageUrl() {
        return consultantImageUrl;
    }

    public void setConsultantImageUrl(String consultantImageUrl) {
        this.consultantImageUrl = consultantImageUrl;
    }

    public String getConsultantTel() {
        return consultantTel;
    }

    public void setConsultantTel(String consultantTel) {
        this.consultantTel = consultantTel;
    }

    public String getConsultantEmail() {
        return consultantEmail;
    }

    public void setConsultantEmail(String consultantEmail) {
        this.consultantEmail = consultantEmail;
    }

    public Byte getConsultantDelYn() {
        return consultantDelYn;
    }

    public void setConsultantDelYn(Byte consultantDelYn) {
        this.consultantDelYn = consultantDelYn;
    }

    public Integer getConsultantRegUserSeq() {
        return consultantRegUserSeq;
    }

    public void setConsultantRegUserSeq(Integer consultantRegUserSeq) {
        this.consultantRegUserSeq = consultantRegUserSeq;
    }

    public Timestamp getConsultantRegDate() {
        return consultantRegDate;
    }

    public void setConsultantRegDate(Timestamp consultantRegDate) {
        this.consultantRegDate = consultantRegDate;
    }

    public Integer getConsultantModUserSeq() {
        return consultantModUserSeq;
    }

    public void setConsultantModUserSeq(Integer consultantModUserSeq) {
        this.consultantModUserSeq = consultantModUserSeq;
    }

    public Timestamp getConsultantModDate() {
        return consultantModDate;
    }

    public void setConsultantModDate(Timestamp consultantModDate) {
        this.consultantModDate = consultantModDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultantEntity that = (ConsultantEntity) o;
        return consultantSeq == that.consultantSeq && productGroupSeq == that.productGroupSeq && Objects.equals(consultantId, that.consultantId) && Objects.equals(consultantPass, that.consultantPass) && Objects.equals(consultantName, that.consultantName) && Objects.equals(consultantImageUrl, that.consultantImageUrl) && Objects.equals(consultantTel, that.consultantTel) && Objects.equals(consultantEmail, that.consultantEmail) && Objects.equals(consultantDelYn, that.consultantDelYn) && Objects.equals(consultantRegUserSeq, that.consultantRegUserSeq) && Objects.equals(consultantRegDate, that.consultantRegDate) && Objects.equals(consultantModUserSeq, that.consultantModUserSeq) && Objects.equals(consultantModDate, that.consultantModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultantSeq, productGroupSeq, consultantId, consultantPass, consultantName, consultantImageUrl, consultantTel, consultantEmail, consultantDelYn, consultantRegUserSeq, consultantRegDate, consultantModUserSeq, consultantModDate);
    }
}
