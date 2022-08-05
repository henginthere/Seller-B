package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@SQLDelete(sql = "UPDATE t_orderdetail SET orderDetail_del_yn=true WHERE order_detail_seq=?")
@Where(clause = "orderDetail_del_yn=false")
@Table(name = "t_orderdetail", schema = "sellerb", catalog = "")
public class Orderdetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_detail_seq")
    private Long orderDetailSeq;
    @ManyToOne
    @JoinColumn(name = "product_seq")
    @JsonBackReference
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_seq")
    @JsonBackReference
    private Order order;
    @Basic
    @Column(name = "order_detail_count")
    private Integer orderDetailCount;
    @Basic
    @Column(name = "orderDetail_del_yn",columnDefinition = "boolean default false")
    private Boolean orderDetailDelYn;
    @CreatedBy
    @Basic
    @Column(name = "order_detail_reg_user_seq")
    private Long orderDetailRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "order_detail_reg_date")
    private LocalDateTime orderDetailRegDate;

    @LastModifiedBy
    @Basic
    @Column(name = "order_detail_mod_user_seq")
    private Long orderDetailModUserSeq;

    @LastModifiedDate
    @Basic
    @Column(name = "order_detail_mod_date")
    private LocalDateTime orderDetailModDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderdetail that = (Orderdetail) o;
        return orderDetailSeq == that.orderDetailSeq && product == that.product && order == that.order && Objects.equals(orderDetailCount, that.orderDetailCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailSeq, product, order, orderDetailCount);
    }
}
