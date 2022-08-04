package backend.sellerB.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
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
@Table(name = "t_wating_customer", schema = "sellerb", catalog = "")
public class WatingCustomer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "waiting_customer_seq")
    private Long waitingCustomerSeq;
    @ManyToOne
    @JoinColumn(name = "customer_seq")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;
    @Basic
    @Column(name = "waiting_customer_state",columnDefinition = "byte default 0")
    private Byte waitingCustomerState;
    @Basic
    @Column(name = "wating_customer_date")
    private LocalDateTime watingCustomerDate;
    @Basic
    @Column(name = "watingCustomer_group_del_yn",columnDefinition = "boolean default false")
    private Boolean watingCustomerGroupDelYn;
    @CreatedBy
    @Basic
    @Column(name = "wating_customer_reg_user_seq")
    private Long waitingCustomerRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "wating_customer_reg_date")
    private LocalDateTime waitingCustomerRegDate;

    @LastModifiedBy
    @Basic
    @Column(name = "wating_customer_mod_user_seq")
    private Long waitingCustomerModUserSeq;

    @LastModifiedDate
    @Basic
    @Column(name = "wating_customer_mod_date")
    private LocalDateTime waitingCustomerModDate;


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
