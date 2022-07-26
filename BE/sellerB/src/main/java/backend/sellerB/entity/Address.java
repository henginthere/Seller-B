package backend.sellerB.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_address", schema = "sellerb", catalog = "")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "addr_seq")
    private int addrSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_seq")
    private Customer customer;
    @Basic
    @Column(name = "addr")
    private String addr;
    @Basic
    @Column(name = "addr_requests")
    private String addrRequests;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return addrSeq == that.addrSeq && customer == that.customer && Objects.equals(addr, that.addr) && Objects.equals(addrRequests, that.addrRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addrSeq, customer, addr, addrRequests);
    }
}
