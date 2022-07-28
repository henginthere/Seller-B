package backend.sellerB.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_product_group", schema = "sellerb", catalog = "")
public class ProductGroup {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_group_seq")
    private int productGroupSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_seq")
    private Brand brand;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroup that = (ProductGroup) o;
        return productGroupSeq == that.productGroupSeq && brand == that.brand && Objects.equals(productGroupCode, that.productGroupCode) && Objects.equals(productGroupName, that.productGroupName) && Objects.equals(productGroupDelYn, that.productGroupDelYn) && Objects.equals(productGroupRegUserSeq, that.productGroupRegUserSeq) && Objects.equals(productGroupRegDate, that.productGroupRegDate) && Objects.equals(productGroupModSeq, that.productGroupModSeq) && Objects.equals(productGroupModDate, that.productGroupModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupSeq, brand, productGroupCode, productGroupName, productGroupDelYn, productGroupRegUserSeq, productGroupRegDate, productGroupModSeq, productGroupModDate);
    }
}
