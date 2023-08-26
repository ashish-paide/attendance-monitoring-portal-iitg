package project.attendancebackend.model;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @Column(name = "student_id")
    String student_id ;

    @Column(name = "student_name")
    String student_name;

    public Student() {
    }

    public Student(String student_id, String student_name) {
        this.student_id = student_id;
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return this.student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Student student_id(String student_id) {
        setStudent_id(student_id);
        return this;
    }

    public Student student_name(String student_name) {
        setStudent_name(student_name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(student_id, student.student_id) && Objects.equals(student_name, student.student_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, student_name);
    }

    @Override
    public String toString() {
        return "{" +
            " student_id='" + getStudent_id() + "'" +
            ", student_name='" + getStudent_name() + "'" +
            "}";
    }


}
