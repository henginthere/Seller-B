package backend.sellerB.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_consulting", schema = "sellerb", catalog = "")
public class Consulting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consulting_seq")
    private int consultingSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerq")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_seq")
    private Consultant consultant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    private Product product;
    @Basic
    @Column(name = "consulting_start_date")
    private Timestamp consultingStartDate;
    @Basic
    @Column(name = "consulting_end_date")
    private Timestamp consultingEndDate;
    @Basic
    @Column(name = "consulting_state")
    private String consultingState;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulting that = (Consulting) o;
        return consultingSeq == that.consultingSeq && customer == that.customer && consultant == that.consultant && product == that.product && Objects.equals(consultingStartDate, that.consultingStartDate) && Objects.equals(consultingEndDate, that.consultingEndDate) && Objects.equals(consultingState, that.consultingState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultingSeq, customer, consultant, product, consultingStartDate, consultingEndDate, consultingState);
    }
}
