package backend.sellerB.entity;

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
@SQLDelete(sql = "UPDATE t_waiting_customer SET waiting_customer_state=true WHERE waiting_customer_seq=?")
@Where(clause = "waiting_customer_state=false")
@Table(name = "t_waiting_customer", schema = "sellerb", catalog = "")
public class WaitingCustomer {
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
    @ManyToOne
    @JoinColumn(name = "product_group_seq")
    private ProductGroup productGroup;
    @Basic
    @Column(name = "waiting_customer_state",columnDefinition = "boolean default false")
    private Boolean waitingCustomerState;
    @Basic
    @Column(name = "waiting_customer_date")
    private LocalDateTime watingCustomerDate;
    @Basic
    @Column(name = "waitingCustomer_group_del_yn",columnDefinition = "boolean default false")
    private Boolean watingCustomerGroupDelYn;
    @CreatedBy
    @Basic
    @Column(name = "waiting_customer_reg_user")
    private String waitingCustomerRegUser;
    @CreatedDate
    @Basic
    @Column(name = "waiting_customer_reg_date")
    private LocalDateTime waitingCustomerRegDate;

    @LastModifiedBy
    @Basic
    @Column(name = "waiting_customer_mod_user")
    private String waitingCustomerModUser;

    @LastModifiedDate
    @Basic
    @Column(name = "waiting_customer_mod_date")
    private LocalDateTime waitingCustomerModDate;


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        WaitingCustomer that = (WaitingCustomer) o;
//        return waitingCustomerSeq == that.waitingCustomerSeq && customer == that.customer && product == that.product && Objects.equals(waitingCustomerState, that.waitingCustomerState) && Objects.equals(watingCustomerDate, that.watingCustomerDate);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(waitingCustomerSeq, customer, product, waitingCustomerState, watingCustomerDate);
//    }
}
