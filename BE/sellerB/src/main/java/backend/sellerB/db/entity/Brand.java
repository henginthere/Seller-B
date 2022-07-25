package backend.sellerB.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_brand", schema = "sellerb", catalog = "")
public class Brand {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "brand_seq")
    private int brandSeq;
    @Basic
    @Column(name = "brand_name_kor")
    private String brandNameKor;
    @Basic
    @Column(name = "brand_name_eng")
    private String brandNameEng;
    @Basic
    @Column(name = "brand_logo")
    private String brandLogo;
    @Basic
    @Column(name = "brand_del_yn")
    private Byte brandDelYn;
    @Basic
    @Column(name = "brand_reg_user_seq")
    private Integer brandRegUserSeq;
    @Basic
    @Column(name = "brand_reg_date")
    private Timestamp brandRegDate;
    @Basic
    @Column(name = "brand_mod_user_seq")
    private Integer brandModUserSeq;
    @Basic
    @Column(name = "brand_mod_date")
    private Timestamp brandModDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand that = (Brand) o;
        return brandSeq == that.brandSeq && Objects.equals(brandNameKor, that.brandNameKor) && Objects.equals(brandNameEng, that.brandNameEng) && Objects.equals(brandLogo, that.brandLogo) && Objects.equals(brandDelYn, that.brandDelYn) && Objects.equals(brandRegUserSeq, that.brandRegUserSeq) && Objects.equals(brandRegDate, that.brandRegDate) && Objects.equals(brandModUserSeq, that.brandModUserSeq) && Objects.equals(brandModDate, that.brandModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandSeq, brandNameKor, brandNameEng, brandLogo, brandDelYn, brandRegUserSeq, brandRegDate, brandModUserSeq, brandModDate);
    }
}
