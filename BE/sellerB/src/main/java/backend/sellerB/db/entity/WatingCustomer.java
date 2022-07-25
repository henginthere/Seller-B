package backend.sellerB.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_wating_customer", schema = "sellerb", catalog = "")
public class WatingCustomer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "waiting_customer_seq")
    private int waitingCustomerSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_seq")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    private Product product;
    @Basic
    @Column(name = "waiting_customer_state")
    private Byte waitingCustomerState;
    @Basic
    @Column(name = "wating_customer_date")
    private Timestamp watingCustomerDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatingCustomer that = (WatingCustomer) o;
        return waitingCustomerSeq == that.waitingCustomerSeq && customer == that.customer && product == that.product && Objects.equals(waitingCustomerState, that.waitingCustomerState) && Objects.equals(watingCustomerDate, that.watingCustomerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waitingCustomerSeq, customer, product, waitingCustomerState, watingCustomerDate);
    }
}
