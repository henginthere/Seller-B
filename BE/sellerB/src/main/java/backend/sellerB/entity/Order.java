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
@SQLDelete(sql = "UPDATE t_order SET order_del_yn=true WHERE order_seq=?")
@Where(clause = "order_del_yn=false")
@Table(name = "t_order", schema = "sellerb", catalog = "")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_seq")
    private Long orderSeq;
    @ManyToOne
    @JoinColumn(name = "addr_seq")
    @JsonBackReference
    private Address addr;
    @Basic
    @Column(name = "order_state",columnDefinition = "boolean default 0")
    private Byte orderState;
    @Basic
    @Column(name = "order_del_yn",columnDefinition = "boolean default false")
    private Boolean orderDelYn;
    @CreatedBy
    @Basic
    @Column(name = "order_reg_user_seq")
    private Long orderRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "order_reg_date")
    private LocalDateTime orderRegDate;

    @LastModifiedBy
    @Basic
    @Column(name = "order_mod_user_seq")
    private Long orderModUserSeq;

    @LastModifiedDate
    @Basic
    @Column(name = "order_mod_date")
    private LocalDateTime orderModDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return orderSeq == that.orderSeq && addr == that.addr && Objects.equals(orderModDate, that.orderModDate) && Objects.equals(orderState, that.orderState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderSeq, addr, orderModDate, orderState);
    }
}
