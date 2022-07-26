package backend.sellerB.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_consultant_attendance", schema = "sellerb", catalog = "")
public class ConsultantAttendanceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consultant_attendance_seq")
    private int consultantAttendanceSeq;
    @Basic
    @Column(name = "consultant_seq")
    private int consultantSeq;
    @Basic
    @Column(name = "login_time")
    private Timestamp loginTime;
    @Basic
    @Column(name = "logout_time")
    private Timestamp logoutTime;

    public int getConsultantAttendanceSeq() {
        return consultantAttendanceSeq;
    }

    public void setConsultantAttendanceSeq(int consultantAttendanceSeq) {
        this.consultantAttendanceSeq = consultantAttendanceSeq;
    }

    public int getConsultantSeq() {
        return consultantSeq;
    }

    public void setConsultantSeq(int consultantSeq) {
        this.consultantSeq = consultantSeq;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultantAttendanceEntity that = (ConsultantAttendanceEntity) o;
        return consultantAttendanceSeq == that.consultantAttendanceSeq && consultantSeq == that.consultantSeq && Objects.equals(loginTime, that.loginTime) && Objects.equals(logoutTime, that.logoutTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultantAttendanceSeq, consultantSeq, loginTime, logoutTime);
    }
}
