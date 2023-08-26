package project.attendancebackend.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;


@Entity
public class Attendance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_id")
    String studentId;

    @Column(name = "class_id")
    String classId;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "attendance_present")
    boolean present;


    public Attendance() {
    }

    public Attendance(long id, String studentId, String classId, LocalDate date, boolean present) {
        this.id = id;
        this.studentId = studentId;
        this.classId = classId;
        this.date = date;
        this.present = present;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return this.classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPresent() {
        return this.present;
    }

    public boolean getPresent() {
        return this.present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Attendance id(long id) {
        setId(id);
        return this;
    }

    public Attendance studentId(String studentId) {
        setStudentId(studentId);
        return this;
    }

    public Attendance classId(String classId) {
        setClassId(classId);
        return this;
    }

    public Attendance date(LocalDate date) {
        setDate(date);
        return this;
    }

    public Attendance present(boolean present) {
        setPresent(present);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Attendance)) {
            return false;
        }
        Attendance attendance = (Attendance) o;
        return id == attendance.id && Objects.equals(studentId, attendance.studentId) && Objects.equals(classId, attendance.classId) && Objects.equals(date, attendance.date) && present == attendance.present;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, classId, date, present);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", studentId='" + getStudentId() + "'" +
            ", classId='" + getClassId() + "'" +
            ", date='" + getDate() + "'" +
            ", present='" + isPresent() + "'" +
            "}";
    }
    
}
