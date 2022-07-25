package backend.sellerB.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_product", schema = "sellerb", catalog = "")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_seq")
    private int productSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group_seq")
    private ProductGroup productGroup;
    @Basic
    @Column(name = "product_id")
    private String productId;
    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "product_price")
    private Integer productPrice;
    @Basic
    @Column(name = "product_manual")
    private String productManual;
    @Basic
    @Column(name = "product_thumbnail")
    private String productThumbnail;
    @Basic
    @Column(name = "product_del_yn")
    private Byte productDelYn;
    @Basic
    @Column(name = "product_reg_user_seq")
    private Integer productRegUserSeq;
    @Basic
    @Column(name = "product_reg_date")
    private Timestamp productRegDate;
    @Basic
    @Column(name = "product_mod_user_seq")
    private Integer productModUserSeq;
    @Basic
    @Column(name = "product_mod_date")
    private Timestamp productModDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return productSeq == that.productSeq && productGroup == that.productGroup && Objects.equals(productId, that.productId) && Objects.equals(productName, that.productName) && Objects.equals(productPrice, that.productPrice) && Objects.equals(productManual, that.productManual) && Objects.equals(productThumbnail, that.productThumbnail) && Objects.equals(productDelYn, that.productDelYn) && Objects.equals(productRegUserSeq, that.productRegUserSeq) && Objects.equals(productRegDate, that.productRegDate) && Objects.equals(productModUserSeq, that.productModUserSeq) && Objects.equals(productModDate, that.productModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productSeq, productGroup, productId, productName, productPrice, productManual, productThumbnail, productDelYn, productRegUserSeq, productRegDate, productModUserSeq, productModDate);
    }
}
