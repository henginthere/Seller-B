package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_product", schema = "sellerb", catalog = "")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_seq")
    private int productSeq;
    @Basic
    @Column(name = "product_group_seq")
    private int productGroupSeq;
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

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public int getProductGroupSeq() {
        return productGroupSeq;
    }

    public void setProductGroupSeq(int productGroupSeq) {
        this.productGroupSeq = productGroupSeq;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductManual() {
        return productManual;
    }

    public void setProductManual(String productManual) {
        this.productManual = productManual;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public Byte getProductDelYn() {
        return productDelYn;
    }

    public void setProductDelYn(Byte productDelYn) {
        this.productDelYn = productDelYn;
    }

    public Integer getProductRegUserSeq() {
        return productRegUserSeq;
    }

    public void setProductRegUserSeq(Integer productRegUserSeq) {
        this.productRegUserSeq = productRegUserSeq;
    }

    public Timestamp getProductRegDate() {
        return productRegDate;
    }

    public void setProductRegDate(Timestamp productRegDate) {
        this.productRegDate = productRegDate;
    }

    public Integer getProductModUserSeq() {
        return productModUserSeq;
    }

    public void setProductModUserSeq(Integer productModUserSeq) {
        this.productModUserSeq = productModUserSeq;
    }

    public Timestamp getProductModDate() {
        return productModDate;
    }

    public void setProductModDate(Timestamp productModDate) {
        this.productModDate = productModDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return productSeq == that.productSeq && productGroupSeq == that.productGroupSeq && Objects.equals(productId, that.productId) && Objects.equals(productName, that.productName) && Objects.equals(productPrice, that.productPrice) && Objects.equals(productManual, that.productManual) && Objects.equals(productThumbnail, that.productThumbnail) && Objects.equals(productDelYn, that.productDelYn) && Objects.equals(productRegUserSeq, that.productRegUserSeq) && Objects.equals(productRegDate, that.productRegDate) && Objects.equals(productModUserSeq, that.productModUserSeq) && Objects.equals(productModDate, that.productModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productSeq, productGroupSeq, productId, productName, productPrice, productManual, productThumbnail, productDelYn, productRegUserSeq, productRegDate, productModUserSeq, productModDate);
    }
}
