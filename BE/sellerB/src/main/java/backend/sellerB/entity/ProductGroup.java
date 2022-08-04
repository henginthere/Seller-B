package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
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
    @Column(name = "product_group_code")
    private String productGroupCode;
    @Basic
    @Column(name = "product_group_name")
    private String productGroupName;
    @Basic
    @Column(name = "product_group_del_yn",columnDefinition = "boolean default false")
    private Boolean productGroupDelYn;
    @CreatedBy
    @Basic
    @Column(name = "product_group_reg_user_seq")
    private Long productGroupRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "product_group_reg_date")
    private LocalDateTime productGroupRegDate;
    @LastModifiedBy
    @Basic
    @Column(name = "product_group_mod_seq")
    private Long productGroupModSeq;
    @LastModifiedDate
    @Basic
    @Column(name = "product_group_mod_date")
    private LocalDateTime productGroupModDate;


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
