package backend.sellerB.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "t_consultant_attendance", schema = "sellerb", catalog = "")
public class ConsultantAttendance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consultant_attendance_seq")
    private int consultantAttendanceSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_seq")
    private Consultant consultant;
    @Basic
    @Column(name = "login_time")
    private Timestamp loginTime;
    @Basic
    @Column(name = "logout_time")
    private Timestamp logoutTime;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultantAttendance that = (ConsultantAttendance) o;
        return consultantAttendanceSeq == that.consultantAttendanceSeq && consultant == that.consultant && Objects.equals(loginTime, that.loginTime) && Objects.equals(logoutTime, that.logoutTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultantAttendanceSeq, consultant, loginTime, logoutTime);
    }
}
