package backend.sellerB.db.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_customer_waiting_page", schema = "sellerb", catalog = "")
public class CustomerWaitingPageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_waiting_page_seq")
    private int customerWaitingPageSeq;
    @Basic
    @Column(name = "product_seq")
    private int productSeq;
    @Basic
    @Column(name = "customer_waiting_page_ment")
    private String customerWaitingPageMent;
    @Basic
    @Column(name = "customer_waiting_page_image")
    private String customerWaitingPageImage;

    public int getCustomerWaitingPageSeq() {
        return customerWaitingPageSeq;
    }

    public void setCustomerWaitingPageSeq(int customerWaitingPageSeq) {
        this.customerWaitingPageSeq = customerWaitingPageSeq;
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public String getCustomerWaitingPageMent() {
        return customerWaitingPageMent;
    }

    public void setCustomerWaitingPageMent(String customerWaitingPageMent) {
        this.customerWaitingPageMent = customerWaitingPageMent;
    }

    public String getCustomerWaitingPageImage() {
        return customerWaitingPageImage;
    }

    public void setCustomerWaitingPageImage(String customerWaitingPageImage) {
        this.customerWaitingPageImage = customerWaitingPageImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerWaitingPageEntity that = (CustomerWaitingPageEntity) o;
        return customerWaitingPageSeq == that.customerWaitingPageSeq && productSeq == that.productSeq && Objects.equals(customerWaitingPageMent, that.customerWaitingPageMent) && Objects.equals(customerWaitingPageImage, that.customerWaitingPageImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerWaitingPageSeq, productSeq, customerWaitingPageMent, customerWaitingPageImage);
    }
}
