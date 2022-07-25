package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_brand", schema = "sellerb", catalog = "")
public class BrandEntity {
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

    public int getBrandSeq() {
        return brandSeq;
    }

    public void setBrandSeq(int brandSeq) {
        this.brandSeq = brandSeq;
    }

    public String getBrandNameKor() {
        return brandNameKor;
    }

    public void setBrandNameKor(String brandNameKor) {
        this.brandNameKor = brandNameKor;
    }

    public String getBrandNameEng() {
        return brandNameEng;
    }

    public void setBrandNameEng(String brandNameEng) {
        this.brandNameEng = brandNameEng;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public Byte getBrandDelYn() {
        return brandDelYn;
    }

    public void setBrandDelYn(Byte brandDelYn) {
        this.brandDelYn = brandDelYn;
    }

    public Integer getBrandRegUserSeq() {
        return brandRegUserSeq;
    }

    public void setBrandRegUserSeq(Integer brandRegUserSeq) {
        this.brandRegUserSeq = brandRegUserSeq;
    }

    public Timestamp getBrandRegDate() {
        return brandRegDate;
    }

    public void setBrandRegDate(Timestamp brandRegDate) {
        this.brandRegDate = brandRegDate;
    }

    public Integer getBrandModUserSeq() {
        return brandModUserSeq;
    }

    public void setBrandModUserSeq(Integer brandModUserSeq) {
        this.brandModUserSeq = brandModUserSeq;
    }

    public Timestamp getBrandModDate() {
        return brandModDate;
    }

    public void setBrandModDate(Timestamp brandModDate) {
        this.brandModDate = brandModDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandEntity that = (BrandEntity) o;
        return brandSeq == that.brandSeq && Objects.equals(brandNameKor, that.brandNameKor) && Objects.equals(brandNameEng, that.brandNameEng) && Objects.equals(brandLogo, that.brandLogo) && Objects.equals(brandDelYn, that.brandDelYn) && Objects.equals(brandRegUserSeq, that.brandRegUserSeq) && Objects.equals(brandRegDate, that.brandRegDate) && Objects.equals(brandModUserSeq, that.brandModUserSeq) && Objects.equals(brandModDate, that.brandModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandSeq, brandNameKor, brandNameEng, brandLogo, brandDelYn, brandRegUserSeq, brandRegDate, brandModUserSeq, brandModDate);
    }
}
