package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_order", schema = "sellerb", catalog = "")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_seq")
    private int orderSeq;
    @Basic
    @Column(name = "addr_seq")
    private int addrSeq;
    @Basic
    @Column(name = "order_date")
    private Timestamp orderDate;
    @Basic
    @Column(name = "order_mod_date")
    private Timestamp orderModDate;
    @Basic
    @Column(name = "order_state")
    private String orderState;

    public int getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }

    public int getAddrSeq() {
        return addrSeq;
    }

    public void setAddrSeq(int addrSeq) {
        this.addrSeq = addrSeq;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getOrderModDate() {
        return orderModDate;
    }

    public void setOrderModDate(Timestamp orderModDate) {
        this.orderModDate = orderModDate;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return orderSeq == that.orderSeq && addrSeq == that.addrSeq && Objects.equals(orderDate, that.orderDate) && Objects.equals(orderModDate, that.orderModDate) && Objects.equals(orderState, that.orderState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderSeq, addrSeq, orderDate, orderModDate, orderState);
    }
}
