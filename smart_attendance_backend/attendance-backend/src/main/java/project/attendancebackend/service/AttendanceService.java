package project.attendancebackend.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import project.attendancebackend.model.Attendance;
import project.attendancebackend.repository.AttendanceRepository;

import project.attendancebackend.repository.StudentRepository;


import java.util.List;
import java.util.ArrayList;

@Service
public class AttendanceService {  

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository; 

    @Autowired
    private StudentService studentService;
/* 
    // public ResponseEntity<String> saveAttendanceData(HttpEntity<MultiValueMap<String, Object>> requestEntity) throws JsonMappingException, JsonProcessingException{
    //     ResponseEntity<String> response = restTemplate.exchange(
    //         "http://localhost:5000/predict_roll_numbers", 
    //         HttpMethod.POST,
    //         requestEntity,
    //         String.class
    //     );
    //     String incomingClassId = requestEntity.getBody().get("classId").get(0).toString();
    //     System.out.print("response :");
    //     System.out.println(response);
    //     // save the attendance in the database

    //     String datePattern = "yyyy-MM-dd";
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
    //     String incomingRawDate = String.valueOf(requestEntity.getBody().get("date").get(0));
    //     LocalDate incomingDate = LocalDate.parse(incomingRawDate, formatter);

    //     ObjectMapper objectMapper = new ObjectMapper();
    //     JsonNode jsonNode = objectMapper.readTree(response.getBody());
    //     JsonNode rollNumbersNode = jsonNode.get("roll_numbers");
    
    //     if (rollNumbersNode.isArray()) {
    //     for (JsonNode rollNumberArray : rollNumbersNode) {
    //             if (rollNumberArray.isArray() && rollNumberArray.size() > 0) {
    //             String rollNumber = rollNumberArray.get(0).asText();
                
    //             String studentId = rollNumber;
    //             Attendance attendance = new Attendance();

    //             attendance.setStudentId(studentId);
    //             attendance.setClassId(incomingClassId);
    //             attendance.setDate(incomingDate);
    //             attendance.setPresent(true);

    //             attendanceRepository.save(attendance);

    //             }
    //         }
    //     }

       

    //  return response;

    // }
    */
    public ResponseEntity<String> saveAttendanceData(HttpEntity<MultiValueMap<String, Object>> requestEntity) throws JsonMappingException, JsonProcessingException{
        ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:5000/predict_roll_numbers", 
            HttpMethod.POST,
            requestEntity,
            String.class
        );
        String incomingClassId = requestEntity.getBody().get("classId").get(0).toString();

        // save the attendance in the database

        String datePattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        String incomingRawDate = String.valueOf(requestEntity.getBody().get("date").get(0));
        LocalDate incomingDate = LocalDate.parse(incomingRawDate, formatter);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        JsonNode rollNumbersNode = jsonNode.get("roll_numbers");
        List<String>presentRollNumbers = new ArrayList<>();
        List<String>allRollNos = studentService.getAllRollNOs();
        if (rollNumbersNode.isArray()) {
            for (JsonNode rollNumberArray : rollNumbersNode) {
                if (rollNumberArray.isArray() && rollNumberArray.size() > 0) {
                    String rollNumber = rollNumberArray.get(0).asText();
                    presentRollNumbers.add(rollNumber);
                }
            }
        }
        for(String rollNumber : allRollNos) {
            Attendance attendance = new Attendance();
            attendance.setStudentId(rollNumber);
            attendance.setClassId(incomingClassId);
            attendance.setDate(incomingDate);
            if(presentRollNumbers.contains(rollNumber)){
                attendance.setPresent(true);
            }
            else{
                attendance.setPresent(false);
            }
            attendanceRepository.save(attendance);

        }
        return response;
    }


    public Attendance addAttendance(Attendance attendance){
        return attendanceRepository.save(attendance);
    }

    public Integer getClassesAttendedByStudent(String classs_id , String roll_no ){
        return attendanceRepository.getClassesAttendedByStudent(classs_id, roll_no);
    }

    public Integer noOfClassesHeld(String classs_id){
        return attendanceRepository.gettotalClasses(classs_id);
    }

    public List<Object[]> getAttendanceByDate(String class_id , String date){
        
        // List<AttendanceListDTO> dtoList = attendanceRepository.getAttendanceListByDate(class_id, date);
        // List<AttendanceList> resultList = new ArrayList<>();
        // for (AttendanceListDTO dto : dtoList) {
        //     AttendanceList attendance = new AttendanceList();
        //     // Map properties from DTO to your entity
        //     attendance.setRollNo(dto.getRollNo());
        //     attendance.setName(dto.getName());
        //     attendance.setStatus(dto.isPresent());
        //     resultList.add(attendance);
        // }
        
        // return resultList;
        // return new ArrayList<>() ;
        return attendanceRepository.getAttendanceListByDate(class_id, date);
    }
    
    public Object[] getWhetherPresent(String class_id , String date , String rollNO) {
        return attendanceRepository.getWhetherPresent(class_id, date , rollNO);
    }

    public List<Object[]> getAttendanceListByStudentIdAndCourseId(String rollNo ,String class_id) {
        return attendanceRepository.getAttendanceListByStudentIdAndCourseId(rollNo, class_id);
    }

    public List<Object[]> getTotalPresenListofStudents(String class_id){
        return attendanceRepository.getTotalPresenListofStudents(class_id);
    }

    public List<Object[]> getStudentAttendanceListInCourse(String course_id , String rollNo){
        return attendanceRepository.getStudentAttendanceListInCourse(course_id, rollNo);
    }

    public List<Object[]> getStudentsPresentPerDateByClassId(String class_id){
        return attendanceRepository.getStudentsPresentPerDateByClassId(class_id);
    }

}

