package backend.sellerB.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_orderdetail", schema = "sellerb", catalog = "")
public class Orderdetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_detail_seq")
    private int orderDetailSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_seq")
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
