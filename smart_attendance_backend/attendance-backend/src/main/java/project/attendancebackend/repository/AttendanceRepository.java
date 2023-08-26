package project.attendancebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.attendancebackend.model.Attendance;
import project.attendancebackend.model.AttendanceList;

public interface AttendanceRepository extends JpaRepository<Attendance,Long>{
    

    @Query(nativeQuery = true, value = "SELECT COUNT(*) as attendance FROM attendance WHERE attendance_present = true AND class_id = :class_id AND student_id = :roll_no")
    public Integer getClassesAttendedByStudent(@Param("class_id") String classId, @Param("roll_no") String rollNo);

    //gives the total classes held by a course
    @Query(nativeQuery = true, value = "SELECT COUNT(DISTINCT CONCAT(class_id, '-', date)) AS distinct_pair_count FROM attendance; ")
    public Integer gettotalClasses(@Param("class_id") String classId);

    // @Query(nativeQuery = true, value = "SELECT a.student_id as rollNo, a.attendance_present as present, s.student_name as name FROM attendance a  JOIN students s ON a.student_id = s.student_id where date = :date and class_id = :classId;")
    // public List<AttendanceList> getAttendanceListByDate(@Param("class_id") String classId, @Param("date") String date);

    //gives the list each containing id , name , and present status the it is given by course_id and date
    @Query(nativeQuery = true, value = "SELECT a.student_id as rollNo, s.student_name as name, a.attendance_present as present  FROM attendance a  JOIN students s ON a.student_id = s.student_id where a.date = :date and a.class_id = :class_id")
    public List<Object[]> getAttendanceListByDate(@Param("class_id") String classId, @Param("date") String date);

    //gives whether a student is present in the particular class, date .
    @Query(nativeQuery = true, value = "SELECT attendance_present FROM attendance WHERE class_id = :class_id AND date = :date AND student_id = :rollNo ;")
    public Object[] getWhetherPresent(@Param("class_id") String classId, @Param("date") String date , @Param("rollNo") String rollNo);

    // gives the list date and the present status if a perticular student and in particular class 
    @Query(nativeQuery = true, value = "Select a.date as date   , a.attendance_present as status from attendance as a where a.class_id = :class_id and a.student_id = :student_id")
    public List<Object[]> getAttendanceListByStudentIdAndCourseId(@Param("student_id") String studentId, @Param("class_id") String classId);

    //gives the list of students with number of days the student is present
    @Query(nativeQuery = true, value = "select student_id , count(*) from attendance where attendance_present = true and class_id = :class_id group by student_id")
    public List<Object[]> getTotalPresenListofStudents(@Param("class_id") String studentId);

    //gives the list containing the date and whether the given student is present or not in given given class
    @Query(nativeQuery = true, value = "select date , attendance_present from attendance where class_id = :class_id and student_id = :rollNo")
    public List<Object[]> getStudentAttendanceListInCourse(@Param("class_id") String classId, @Param("rollNo") String rollNo);

    //gives the list containing the date and no. of people present in the given class
    @Query(nativeQuery = true, value = "select date , count(*) from attendance where attendance_present = true and class_id = :class_id group by date")
    public List<Object[]> getStudentsPresentPerDateByClassId(@Param("class_id") String classId);


}
