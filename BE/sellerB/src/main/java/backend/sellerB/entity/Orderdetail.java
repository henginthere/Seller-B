package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_orderdetail", schema = "sellerb", catalog = "")
public class Orderdetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_detail_seq")
    private int orderDetailSeq;
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
