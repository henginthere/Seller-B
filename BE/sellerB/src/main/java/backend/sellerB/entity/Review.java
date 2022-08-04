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
@Table(name = "t_review", schema = "sellerb", catalog = "")
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_seq")
    private Long reviewSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulting_seq")
    private Consulting consulting;
    @Basic
    @Column(name = "review_grade")
    private Integer reviewGrade;
    @Basic
    @Column(name = "review_content")
    private String reviewContent;
    @CreatedBy
    @Basic
    @Column(name = "review_reg_user_seq")
    private Long reviewRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "review_reg_date")
    private LocalDateTime reviewRegDate;
    @LastModifiedBy
    @Basic
    @Column(name = "review_mod_user_seq")
    private Long reviewModUserSeq;
    @LastModifiedDate
    @Basic
    @Column(name = "review_mod_date")
    private LocalDateTime reviewModDate;



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
