package backend.sellerB.db.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_address", schema = "sellerb", catalog = "")
public class AddressEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "addr_seq")
    private int addrSeq;
    @Basic
    @Column(name = "customer_seq")
    private int customerSeq;
    @Basic
    @Column(name = "addr")
    private String addr;
    @Basic
    @Column(name = "addr_requests")
    private String addrRequests;

    public int getAddrSeq() {
        return addrSeq;
    }

    public void setAddrSeq(int addrSeq) {
        this.addrSeq = addrSeq;
    }

    public int getCustomerSeq() {
        return customerSeq;
    }

    public void setCustomerSeq(int customerSeq) {
        this.customerSeq = customerSeq;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddrRequests() {
        return addrRequests;
    }

    public void setAddrRequests(String addrRequests) {
        this.addrRequests = addrRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return addrSeq == that.addrSeq && customerSeq == that.customerSeq && Objects.equals(addr, that.addr) && Objects.equals(addrRequests, that.addrRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addrSeq, customerSeq, addr, addrRequests);
    }
}
