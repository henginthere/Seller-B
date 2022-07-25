package backend.sellerB.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_consultant", schema = "sellerb", catalog = "")
public class Consultant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consultant_seq")
    private int consultantSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group_seq")
    private ProductGroup productGroup;
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
}
