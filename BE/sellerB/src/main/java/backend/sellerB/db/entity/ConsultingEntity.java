package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_consulting", schema = "sellerb", catalog = "")
public class ConsultingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consulting_seq")
    private int consultingSeq;
    @Basic
    @Column(name = "customer_seq")
    private int customerSeq;
    @Basic
    @Column(name = "consultant_seq")
    private int consultantSeq;
    @Basic
    @Column(name = "product_seq")
    private int productSeq;
    @Basic
    @Column(name = "consulting_start_date")
    private Timestamp consultingStartDate;
    @Basic
    @Column(name = "consulting_end_date")
    private Timestamp consultingEndDate;
    @Basic
    @Column(name = "consulting_state")
    private String consultingState;

    public int getConsultingSeq() {
        return consultingSeq;
    }

    public void setConsultingSeq(int consultingSeq) {
        this.consultingSeq = consultingSeq;
    }

    public int getCustomerSeq() {
        return customerSeq;
    }

    public void setCustomerSeq(int customerSeq) {
        this.customerSeq = customerSeq;
    }

    public int getConsultantSeq() {
        return consultantSeq;
    }

    public void setConsultantSeq(int consultantSeq) {
        this.consultantSeq = consultantSeq;
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public Timestamp getConsultingStartDate() {
        return consultingStartDate;
    }

    public void setConsultingStartDate(Timestamp consultingStartDate) {
        this.consultingStartDate = consultingStartDate;
    }

    public Timestamp getConsultingEndDate() {
        return consultingEndDate;
    }

    public void setConsultingEndDate(Timestamp consultingEndDate) {
        this.consultingEndDate = consultingEndDate;
    }

    public String getConsultingState() {
        return consultingState;
    }

    public void setConsultingState(String consultingState) {
        this.consultingState = consultingState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultingEntity that = (ConsultingEntity) o;
        return consultingSeq == that.consultingSeq && customerSeq == that.customerSeq && consultantSeq == that.consultantSeq && productSeq == that.productSeq && Objects.equals(consultingStartDate, that.consultingStartDate) && Objects.equals(consultingEndDate, that.consultingEndDate) && Objects.equals(consultingState, that.consultingState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultingSeq, customerSeq, consultantSeq, productSeq, consultingStartDate, consultingEndDate, consultingState);
    }
}
