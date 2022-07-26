package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_notice", schema = "sellerb", catalog = "")
public class NoticeEntity {
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
    @Basic
    @Column(name = "brand_seq")
    private Integer brandSeq;

    public int getNoticeSeq() {
        return noticeSeq;
    }

    public void setNoticeSeq(int noticeSeq) {
        this.noticeSeq = noticeSeq;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Byte getNoticeDelYn() {
        return noticeDelYn;
    }

    public void setNoticeDelYn(Byte noticeDelYn) {
        this.noticeDelYn = noticeDelYn;
    }

    public Integer getNoticeRegUserSeq() {
        return noticeRegUserSeq;
    }

    public void setNoticeRegUserSeq(Integer noticeRegUserSeq) {
        this.noticeRegUserSeq = noticeRegUserSeq;
    }

    public Timestamp getNoticeRegDate() {
        return noticeRegDate;
    }

    public void setNoticeRegDate(Timestamp noticeRegDate) {
        this.noticeRegDate = noticeRegDate;
    }

    public Integer getNoticeModUserSeq() {
        return noticeModUserSeq;
    }

    public void setNoticeModUserSeq(Integer noticeModUserSeq) {
        this.noticeModUserSeq = noticeModUserSeq;
    }

    public Timestamp getNoticeModDate() {
        return noticeModDate;
    }

    public void setNoticeModDate(Timestamp noticeModDate) {
        this.noticeModDate = noticeModDate;
    }

    public Integer getBrandSeq() {
        return brandSeq;
    }

    public void setBrandSeq(Integer brandSeq) {
        this.brandSeq = brandSeq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeEntity that = (NoticeEntity) o;
        return noticeSeq == that.noticeSeq && Objects.equals(noticeTitle, that.noticeTitle) && Objects.equals(noticeContent, that.noticeContent) && Objects.equals(noticeDelYn, that.noticeDelYn) && Objects.equals(noticeRegUserSeq, that.noticeRegUserSeq) && Objects.equals(noticeRegDate, that.noticeRegDate) && Objects.equals(noticeModUserSeq, that.noticeModUserSeq) && Objects.equals(noticeModDate, that.noticeModDate) && Objects.equals(brandSeq, that.brandSeq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeSeq, noticeTitle, noticeContent, noticeDelYn, noticeRegUserSeq, noticeRegDate, noticeModUserSeq, noticeModDate, brandSeq);
    }
}
