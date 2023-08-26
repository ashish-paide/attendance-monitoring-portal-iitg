package project.attendancebackend.controller;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Service;

import project.attendancebackend.model.Student;
import project.attendancebackend.service.AttendanceService;
import project.attendancebackend.service.StudentService;
import project.attendancebackend.model.Attendance;
import project.attendancebackend.model.AttendanceList;
import project.attendancebackend.model.AttendanceQuery;
import project.attendancebackend.model.AttendanceQuery;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class AttendanceController {

    @Autowired
    StudentService studentService;

    @Autowired
    AttendanceService attendanceService;

    

    @GetMapping("/getAttendancePercentageByRollAndClass/{class_id}/{rollno}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Float postMethodName(
        @PathVariable("class_id") String classId ,
        @PathVariable("rollno") String rollNo) {
        //TODO: process POST request
        Integer present = attendanceService.getClassesAttendedByStudent(classId , rollNo);
        Integer classesHeld = attendanceService.noOfClassesHeld(classId);
        Float percentage = ((float)present * (float)100) / (float) classesHeld;
        
        return percentage;
    }
    
    @GetMapping("/getAttendanceListByDateAndCourse/{class_id}/{date}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Object[]> getAttendanceList(
        @PathVariable("class_id") String classId,
        @PathVariable("date") String date) {

        return attendanceService.getAttendanceByDate(classId, date);
    }

    @GetMapping("/getWhetherPresent/{class_id}/{date}/{rollno}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Object[] getWhetherPresent(
        @PathVariable("class_id") String classId ,
        @PathVariable("date") String date ,
        @PathVariable("rollno") String rollNo){

            return attendanceService.getWhetherPresent(classId, date, rollNo);
    }  
    
    @GetMapping("/getAttendanceListByStudentIdAndCourseId/{class_id}/{rollno}")
    @CrossOrigin(origins = "http://localhost:3000")  
    public List<Object[]> getAttendanceListByStudentIdAndCourseId(
        @PathVariable("rollno") String rollNo ,
        @PathVariable("class_id") String classId){

            return attendanceService.getAttendanceListByStudentIdAndCourseId(rollNo , classId) ;
    }

    //gives the list of students with number of days the student is present
    @GetMapping("/getTotalPresenListofStudents/{course_id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Object[]> getTotalPresenListofStudents(@PathVariable("course_id") String courseId){

        return attendanceService.getTotalPresenListofStudents(courseId) ;
    }

    //gives the list containing the date and no. of people present in the given class
    @GetMapping("/getStudentsPresentPerDateByClassId/{course_id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Object[]> getStudentsPresentPerDateByClassId(@PathVariable("course_id") String courseId){

        return attendanceService.getStudentsPresentPerDateByClassId(courseId) ;
    }

    @GetMapping("/getStudentAttendanceListInCourse/{course_id}/{rollno}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Object[]> getStudentAttendanceListInCourse(
        @PathVariable("course_id") String courseId , 
        @PathVariable("rollno") String rollno){

        return attendanceService.getStudentAttendanceListInCourse(courseId , rollno) ;
    }


    /////////////============DEBIG---apiS========================\\\\\\\\\\\\\\\\\\
    @PostMapping("/DEBUG/addAttendance")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Attendance> MakeAttendance(@RequestBody AttendanceQuery request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(request.getDate(), formatter);

        List<Attendance> attendanceList = new ArrayList<Attendance>();

        List<String> rollNoList = studentService.getAllRollNOs();

        for(String rollNo : rollNoList) {
            Attendance attendance = new Attendance() ;

            attendance.setStudentId(rollNo);
            attendance.setClassId(request.getClassId());
            attendance.setDate(parsedDate);
            System.out.println(rollNoList);
            System.out.println(request.getRollNos());
            if(request.getRollNos().contains(rollNo)){
                attendance.setPresent(true);
            }
            else{
                attendance.setPresent(false);
            }
            attendanceService.addAttendance(attendance);
            attendanceList.add(attendance);
        }

        return attendanceList;
    }
    
}
