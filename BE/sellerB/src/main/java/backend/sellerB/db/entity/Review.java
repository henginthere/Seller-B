package backend.sellerB.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_review", schema = "sellerb", catalog = "")
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_seq")
    private int reviewSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulting_seq")
    private Consulting consulting;
    @Basic
    @Column(name = "review_grade")
    private Integer reviewGrade;
    @Basic
    @Column(name = "review_content")
    private String reviewContent;
    @Basic
    @Column(name = "review_reg_date")
    private Timestamp reviewRegDate;
    @Basic
    @Column(name = "review_mod_date")
    private Timestamp reviewModDate;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review that = (Review) o;
        return reviewSeq == that.reviewSeq && consulting == that.consulting && Objects.equals(reviewGrade, that.reviewGrade) && Objects.equals(reviewContent, that.reviewContent) && Objects.equals(reviewRegDate, that.reviewRegDate) && Objects.equals(reviewModDate, that.reviewModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewSeq, consulting, reviewGrade, reviewContent, reviewRegDate, reviewModDate);
    }
}
