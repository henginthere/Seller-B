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
@SQLDelete(sql = "UPDATE t_address SET address_del_yn=true WHERE address_seq=?")
@Where(clause = "addr_del_yn=false")
@Table(name = "t_address", schema = "sellerb", catalog = "")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "addr_seq")
    private Long addrSeq;
    @ManyToOne
    @JoinColumn(name = "customer_seq")
    @JsonBackReference
    private Customer customer;
    @Basic
    @Column(name = "addr", length = 100)
    private String addr;
    @Basic
    @Column(name = "addr_requests",length = 100)
    private String addrRequests;

    @Basic
    @Column(name = "addr_del_yn",columnDefinition = "boolean default false")
    private Boolean addrDelYn;
    @CreatedBy
    @Basic
    @Column(name = "addr_reg_user_seq")
    private Long addrRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "addr_reg_date")
    private LocalDateTime addrRegDate;
    @LastModifiedBy
    @Basic
    @Column(name = "addr_mod_user_seq")
    private Long addrModUserSeq;
    @LastModifiedDate
    @Basic
    @Column(name = "addr_mod_date")
    private LocalDateTime addrModDate;

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
