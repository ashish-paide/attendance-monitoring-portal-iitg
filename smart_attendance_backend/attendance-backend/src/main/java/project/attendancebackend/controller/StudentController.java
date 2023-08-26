package project.attendancebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.attendancebackend.model.Student;
import project.attendancebackend.service.StudentService;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping("/studentNameById/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> getStudentById(@Param("id") String student_id){
        Student student = studentService.getStudentById(student_id);
        return ResponseEntity.ok(student.getStudent_name());
    }
    @PostMapping("/getStudentsById")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Student> GetStudentsById(@RequestBody List<String> attendance) {
        List<Student> students = new ArrayList<Student>();
        for(String rollNo : attendance){
            Student student = studentService.getStudentById(rollNo) ;
            students.add(student);
        }
        return students;
    }

    // nevermind stuff VVVVVVV 
    @PostMapping("/Debug/addStudents")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Student> addStudents(@RequestBody List<Student> students){
        System.out.println("adding students");
        return studentService.saveAllStudents(students);
    }
    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> justChech(){
        System.out.println("just Chekking") ;
        return ResponseEntity.ok("working");
    }
    @GetMapping("/listofStudents")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<String> GetAllRollNos(){

        return studentService.getAllRollNOs();
        
    }
}
