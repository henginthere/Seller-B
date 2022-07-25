package backend.sellerB.db.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_orderdetail", schema = "sellerb", catalog = "")
public class OrderdetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_detail_seq")
    private int orderDetailSeq;
    @Basic
    @Column(name = "product_seq")
    private int productSeq;
    @Basic
    @Column(name = "order_seq")
    private int orderSeq;
    @Basic
    @Column(name = "order_detail_count")
    private Integer orderDetailCount;

    public int getOrderDetailSeq() {
        return orderDetailSeq;
    }

    public void setOrderDetailSeq(int orderDetailSeq) {
        this.orderDetailSeq = orderDetailSeq;
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public int getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }

    public Integer getOrderDetailCount() {
        return orderDetailCount;
    }

    public void setOrderDetailCount(Integer orderDetailCount) {
        this.orderDetailCount = orderDetailCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderdetailEntity that = (OrderdetailEntity) o;
        return orderDetailSeq == that.orderDetailSeq && productSeq == that.productSeq && orderSeq == that.orderSeq && Objects.equals(orderDetailCount, that.orderDetailCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailSeq, productSeq, orderSeq, orderDetailCount);
    }
}
