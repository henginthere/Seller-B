package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE t_brand SET brand_del_yn=true WHERE brand_seq=?")
@Where(clause = "brand_del_yn=false")
@Table(name = "t_brand", schema = "sellerb", catalog = "")
public class Brand {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "brand_seq")
    private Long brandSeq;
    @Basic
    @Column(name = "brand_name_kor",length = 25)
    private String brandNameKor;
    @Basic
    @Column(name = "brand_name_eng",length = 25)
    private String brandNameEng;
    @Basic
    @Column(name = "brand_logo")
    private String brandLogo;
    @Basic
    @Column(name = "brand_del_yn",columnDefinition = "boolean default false")
    private Boolean brandDelYn;
    @CreatedBy
    @Basic
    @Column(name = "brand_reg_user_seq")
    private Long brandRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "brand_reg_date")
    private LocalDateTime brandRegDate;
    @LastModifiedBy
    @Basic
    @Column(name = "brand_mod_user_seq")
    private Long brandModUserSeq;
    @LastModifiedDate
    @Basic
    @Column(name = "brand_mod_date")
    private LocalDateTime brandModDate;

    @OneToMany(mappedBy = "brand")
    @JsonManagedReference
    private List<ProductGroup> productGroups = new ArrayList<ProductGroup>();

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
