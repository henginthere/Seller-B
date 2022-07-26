package backend.sellerB.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Byte noticeDelYn;
    @Basic
    @Column(name = "notice_reg_user_seq")
    private Integer noticeRegUserSeq;
    @Basic
    @Column(name = "notice_reg_date")
    private Timestamp noticeRegDate;
    @Basic
    @Column(name = "notice_mod_user_seq")
    private Integer noticeModUserSeq;
    @Basic
    @Column(name = "notice_mod_date")
    private Timestamp noticeModDate;
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
