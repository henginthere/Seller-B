package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_wating_customer", schema = "sellerb", catalog = "")
public class WatingCustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "waiting_customer_seq")
    private int waitingCustomerSeq;
    @Basic
    @Column(name = "customer_seq")
    private int customerSeq;
    @Basic
    @Column(name = "product_seq")
    private int productSeq;
    @Basic
    @Column(name = "waiting_customer_state")
    private Byte waitingCustomerState;
    @Basic
    @Column(name = "wating_customer_date")
    private Timestamp watingCustomerDate;

    public int getWaitingCustomerSeq() {
        return waitingCustomerSeq;
    }

    public void setWaitingCustomerSeq(int waitingCustomerSeq) {
        this.waitingCustomerSeq = waitingCustomerSeq;
    }

    public int getCustomerSeq() {
        return customerSeq;
    }

    public void setCustomerSeq(int customerSeq) {
        this.customerSeq = customerSeq;
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public Byte getWaitingCustomerState() {
        return waitingCustomerState;
    }

    public void setWaitingCustomerState(Byte waitingCustomerState) {
        this.waitingCustomerState = waitingCustomerState;
    }

    public Timestamp getWatingCustomerDate() {
        return watingCustomerDate;
    }

    public void setWatingCustomerDate(Timestamp watingCustomerDate) {
        this.watingCustomerDate = watingCustomerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatingCustomerEntity that = (WatingCustomerEntity) o;
        return waitingCustomerSeq == that.waitingCustomerSeq && customerSeq == that.customerSeq && productSeq == that.productSeq && Objects.equals(waitingCustomerState, that.waitingCustomerState) && Objects.equals(watingCustomerDate, that.watingCustomerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waitingCustomerSeq, customerSeq, productSeq, waitingCustomerState, watingCustomerDate);
    }
}
