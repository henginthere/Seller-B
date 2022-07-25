package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_review", schema = "sellerb", catalog = "")
public class ReviewEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_seq")
    private int reviewSeq;
    @Basic
    @Column(name = "consulting_seq")
    private int consultingSeq;
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

    public int getReviewSeq() {
        return reviewSeq;
    }

    public void setReviewSeq(int reviewSeq) {
        this.reviewSeq = reviewSeq;
    }

    public int getConsultingSeq() {
        return consultingSeq;
    }

    public void setConsultingSeq(int consultingSeq) {
        this.consultingSeq = consultingSeq;
    }

    public Integer getReviewGrade() {
        return reviewGrade;
    }

    public void setReviewGrade(Integer reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Timestamp getReviewRegDate() {
        return reviewRegDate;
    }

    public void setReviewRegDate(Timestamp reviewRegDate) {
        this.reviewRegDate = reviewRegDate;
    }

    public Timestamp getReviewModDate() {
        return reviewModDate;
    }

    public void setReviewModDate(Timestamp reviewModDate) {
        this.reviewModDate = reviewModDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity that = (ReviewEntity) o;
        return reviewSeq == that.reviewSeq && consultingSeq == that.consultingSeq && Objects.equals(reviewGrade, that.reviewGrade) && Objects.equals(reviewContent, that.reviewContent) && Objects.equals(reviewRegDate, that.reviewRegDate) && Objects.equals(reviewModDate, that.reviewModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewSeq, consultingSeq, reviewGrade, reviewContent, reviewRegDate, reviewModDate);
    }
}
