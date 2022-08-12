package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
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
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE t_customer_waiting_page SET customer_waiting_page_del_yn=true WHERE customer_waiting_page_seq=?")
@Where(clause = "customer_waiting_page_del_yn=false")
@Table(name = "t_customer_waiting_page", schema = "sellerb", catalog = "")
public class CustomerWaitingPage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_waiting_page_seq")
    private Long customerWaitingPageSeq;
    @ManyToOne
    @JoinColumn(name = "product_seq")
    @JsonBackReference
    private Product product;
    @Basic
    @Column(name = "customer_waiting_page_ment")
    private String customerWaitingPageMent;
    @Basic
    @Column(name = "customer_waiting_page_image")
    private String customerWaitingPageImage;

    @Basic
    @Column(name = "customer_waiting_page_del_yn",columnDefinition = "boolean default false")
    private Boolean customerWaitingPageDelYn;

    @CreatedBy
    @Basic
    @Column(name = "customer_waiting_page_reg_user")
    private String customerWaitingPageRegUser;

    @CreatedDate
    @Basic
    @Column(name = "customer_waiting_page_reg_date")
    private LocalDateTime customerWaitingPageRegDate;

    @LastModifiedBy
    @Basic
    @Column(name = "customer_waiting_page_mod_user")
    private String customerWaitingPageModUser;

    @LastModifiedDate
    @Basic
    @Column(name = "customer_waiting_page_mod_date")
    private LocalDateTime customerWaitingPageModDate;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerWaitingPage that = (CustomerWaitingPage) o;
        return customerWaitingPageSeq == that.customerWaitingPageSeq && product == that.product && Objects.equals(customerWaitingPageMent, that.customerWaitingPageMent) && Objects.equals(customerWaitingPageImage, that.customerWaitingPageImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerWaitingPageSeq, product, customerWaitingPageMent, customerWaitingPageImage);
    }
}
