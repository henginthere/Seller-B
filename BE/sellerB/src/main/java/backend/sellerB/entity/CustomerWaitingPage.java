package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_customer_waiting_page", schema = "sellerb", catalog = "")
public class CustomerWaitingPage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_waiting_page_seq")
    private int customerWaitingPageSeq;
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
