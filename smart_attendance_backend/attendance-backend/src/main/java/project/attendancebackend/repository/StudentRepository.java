package project.attendancebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.attendancebackend.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    
    @Query(nativeQuery = true, value = "SELECT DISTINCT student_id FROM students")
    public List<String> getAllRollNos();
}
