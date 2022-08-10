package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
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
@SQLDelete(sql = "UPDATE t_notice SET notice_del_yn=true WHERE notice_seq=?")
@Where(clause = "notice_del_yn=false")
@Table(name = "t_notice", schema = "sellerb", catalog = "")
public class Notice implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "notice_seq")
    private Long noticeSeq;
    @ManyToOne
    @JoinColumn(name = "brand_seq")
    @JsonBackReference
    private Brand brandSeq;
    @Basic
    @Column(name = "notice_title", length = 100)
    private String noticeTitle;
    @Basic
    @Column(name = "notice_content", length = 500)
    private String noticeContent;
    @Basic
    @Column(name = "notice_del_yn",columnDefinition = "boolean default false")
    private Boolean noticeDelYn;

    @CreatedBy
    @Basic
    @Column(name = "notice_reg_user")
    private String noticeRegUser;
    @CreatedDate
    @Basic
    @Column(name = "notice_reg_date")
    private LocalDateTime noticeRegDate;

    @LastModifiedBy
    @Basic
    @Column(name = "notice_mod_user")
    private String noticeModUser;

    @LastModifiedDate
    @Basic
    @Column(name = "notice_mod_date")
    private LocalDateTime noticeModDate;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice that = (Notice) o;
        return noticeSeq == that.noticeSeq && Objects.equals(noticeTitle, that.noticeTitle) && Objects.equals(noticeContent, that.noticeContent) && Objects.equals(noticeDelYn, that.noticeDelYn) && Objects.equals(noticeRegUser, that.noticeRegUser) && Objects.equals(noticeRegDate, that.noticeRegDate) && Objects.equals(noticeModUser, that.noticeModUser) && Objects.equals(noticeModDate, that.noticeModDate) && Objects.equals(brandSeq, that.brandSeq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeSeq, noticeTitle, noticeContent, noticeDelYn, noticeRegUser, noticeRegDate, noticeModUser, noticeModDate, brandSeq);
    }
}
