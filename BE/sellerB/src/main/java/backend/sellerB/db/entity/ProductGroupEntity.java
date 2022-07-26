package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_product_group", schema = "sellerb", catalog = "")
public class ProductGroupEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_group_seq")
    private int productGroupSeq;
    @Basic
    @Column(name = "brand_seq")
    private int brandSeq;
    @Basic
    @Column(name = "product_group_code")
    private String productGroupCode;
    @Basic
    @Column(name = "product_group_name")
    private String productGroupName;
    @Basic
    @Column(name = "product_group_del_yn")
    private Byte productGroupDelYn;
    @Basic
    @Column(name = "product_group_reg_user_seq")
    private Integer productGroupRegUserSeq;
    @Basic
    @Column(name = "product_group_reg_date")
    private Timestamp productGroupRegDate;
    @Basic
    @Column(name = "product_group_mod_seq")
    private Integer productGroupModSeq;
    @Basic
    @Column(name = "product_group_mod_date")
    private Timestamp productGroupModDate;

    public int getProductGroupSeq() {
        return productGroupSeq;
    }

    public void setProductGroupSeq(int productGroupSeq) {
        this.productGroupSeq = productGroupSeq;
    }

    public int getBrandSeq() {
        return brandSeq;
    }

    public void setBrandSeq(int brandSeq) {
        this.brandSeq = brandSeq;
    }

    public String getProductGroupCode() {
        return productGroupCode;
    }

    public void setProductGroupCode(String productGroupCode) {
        this.productGroupCode = productGroupCode;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public Byte getProductGroupDelYn() {
        return productGroupDelYn;
    }

    public void setProductGroupDelYn(Byte productGroupDelYn) {
        this.productGroupDelYn = productGroupDelYn;
    }

    public Integer getProductGroupRegUserSeq() {
        return productGroupRegUserSeq;
    }

    public void setProductGroupRegUserSeq(Integer productGroupRegUserSeq) {
        this.productGroupRegUserSeq = productGroupRegUserSeq;
    }

    public Timestamp getProductGroupRegDate() {
        return productGroupRegDate;
    }

    public void setProductGroupRegDate(Timestamp productGroupRegDate) {
        this.productGroupRegDate = productGroupRegDate;
    }

    public Integer getProductGroupModSeq() {
        return productGroupModSeq;
    }

    public void setProductGroupModSeq(Integer productGroupModSeq) {
        this.productGroupModSeq = productGroupModSeq;
    }

    public Timestamp getProductGroupModDate() {
        return productGroupModDate;
    }

    public void setProductGroupModDate(Timestamp productGroupModDate) {
        this.productGroupModDate = productGroupModDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroupEntity that = (ProductGroupEntity) o;
        return productGroupSeq == that.productGroupSeq && brandSeq == that.brandSeq && Objects.equals(productGroupCode, that.productGroupCode) && Objects.equals(productGroupName, that.productGroupName) && Objects.equals(productGroupDelYn, that.productGroupDelYn) && Objects.equals(productGroupRegUserSeq, that.productGroupRegUserSeq) && Objects.equals(productGroupRegDate, that.productGroupRegDate) && Objects.equals(productGroupModSeq, that.productGroupModSeq) && Objects.equals(productGroupModDate, that.productGroupModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupSeq, brandSeq, productGroupCode, productGroupName, productGroupDelYn, productGroupRegUserSeq, productGroupRegDate, productGroupModSeq, productGroupModDate);
    }
}
