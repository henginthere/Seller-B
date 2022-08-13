package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE t_product_group SET product_group_del_yn=true WHERE product_group_seq=?")
@Where(clause = "product_group_del_yn=false")
@Table(name = "t_product_group", schema = "sellerb", catalog = "")
public class ProductGroup {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_group_seq")
    private Long productGroupSeq;

    @ManyToOne
    @JoinColumn(name = "brand_seq")
    @JsonBackReference
    private Brand brand;
    @Basic
    @Column(name = "product_group_code", length = 10)
    private String productGroupCode;
    @Basic
    @Column(name = "product_group_name", length = 25)
    private String productGroupName;
    @Basic
    @Column(name = "product_group_del_yn",columnDefinition = "boolean default false")
    private Boolean productGroupDelYn;
    @CreatedBy
    @Basic
    @Column(name = "product_group_reg_user")
    private String productGroupRegUser;
    @CreatedDate
    @Basic
    @Column(name = "product_group_reg_date")
    private LocalDateTime productGroupRegDate;
    @LastModifiedBy
    @Basic
    @Column(name = "product_group_mod")
    private String productGroupMod;
    @LastModifiedDate
    @Basic
    @Column(name = "product_group_mod_date")
    private LocalDateTime productGroupModDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroup that = (ProductGroup) o;
        return productGroupSeq == that.productGroupSeq && brand == that.brand && Objects.equals(productGroupCode, that.productGroupCode) && Objects.equals(productGroupName, that.productGroupName) && Objects.equals(productGroupDelYn, that.productGroupDelYn) && Objects.equals(productGroupRegUser, that.productGroupRegUser) && Objects.equals(productGroupRegDate, that.productGroupRegDate) && Objects.equals(productGroupMod, that.productGroupMod) && Objects.equals(productGroupModDate, that.productGroupModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupSeq, brand, productGroupCode, productGroupName, productGroupDelYn, productGroupRegUser, productGroupRegDate, productGroupMod, productGroupModDate);
    }
}
