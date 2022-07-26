package backend.sellerB.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_notice", schema = "sellerb", catalog = "")
public class Notice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "notice_seq")
    private int noticeSeq;
    @Basic
    @Column(name = "notice_title")
    private String noticeTitle;
    @Basic
    @Column(name = "notice_content")
    private String noticeContent;
    @Basic
    @Column(name = "notice_del_yn")
    private String noticeDelYn;

    @CreatedBy
    @Basic
    @Column(name = "notice_reg_user_seq")
    private String noticeRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "notice_reg_date")
    private LocalDateTime noticeRegDate;

    @LastModifiedBy
    @Basic
    @Column(name = "notice_mod_user_seq")
    private String noticeModUserSeq;

    @LastModifiedDate
    @Basic
    @Column(name = "notice_mod_date")
    private LocalDateTime noticeModDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_seq")
    private Brand brandSeq;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice that = (Notice) o;
        return noticeSeq == that.noticeSeq && Objects.equals(noticeTitle, that.noticeTitle) && Objects.equals(noticeContent, that.noticeContent) && Objects.equals(noticeDelYn, that.noticeDelYn) && Objects.equals(noticeRegUserSeq, that.noticeRegUserSeq) && Objects.equals(noticeRegDate, that.noticeRegDate) && Objects.equals(noticeModUserSeq, that.noticeModUserSeq) && Objects.equals(noticeModDate, that.noticeModDate) && Objects.equals(brandSeq, that.brandSeq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeSeq, noticeTitle, noticeContent, noticeDelYn, noticeRegUserSeq, noticeRegDate, noticeModUserSeq, noticeModDate, brandSeq);
    }
}
