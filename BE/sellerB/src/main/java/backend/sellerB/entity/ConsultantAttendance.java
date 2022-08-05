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
import java.time.LocalDate;
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
@Table(name = "t_consultant_attendance", schema = "sellerb", catalog = "")
public class ConsultantAttendance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "consultant_attendance_seq")
    private Long consultantAttendanceSeq;
    @ManyToOne
    @JoinColumn(name = "consultant_seq")
    private Consultant consultant;

    @CreatedBy
    @Basic
    @Column(name = "consultant_attendance_reg_user_seq")
    private Long consultantAttendanceRegUserSeq;
    @CreatedDate
    @Basic
    @Column(name = "login_time")
    private LocalDateTime loginTime;

    @LastModifiedBy
    @Basic
    @Column(name = "consultant_attendance_mod_user_seq")
    private Long consultantAttendanceModUserSeq;
    @LastModifiedDate
    @Basic
    @Column(name = "logout_time")
    private LocalDateTime logoutTime;

    //0이면 로그인, 1이면 로그아웃
    @Basic
    @Column(name = "consultant_attendance_state",columnDefinition = "boolean default false")
    private Boolean consultantAttendanceState;


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
