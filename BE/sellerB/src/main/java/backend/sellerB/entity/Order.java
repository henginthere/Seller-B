package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_order", schema = "sellerb", catalog = "")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_seq")
    private int orderSeq;
    @ManyToOne
    @JoinColumn(name = "addr_seq")
    @JsonBackReference
    private Address addr;
    @Basic
    @Column(name = "order_date")
    private Timestamp orderDate;
    @Basic
    @Column(name = "order_mod_date")
    private Timestamp orderModDate;
    @Basic
    @Column(name = "order_state")
    private String orderState;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return orderSeq == that.orderSeq && addr == that.addr && Objects.equals(orderDate, that.orderDate) && Objects.equals(orderModDate, that.orderModDate) && Objects.equals(orderState, that.orderState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderSeq, addr, orderDate, orderModDate, orderState);
    }
}
