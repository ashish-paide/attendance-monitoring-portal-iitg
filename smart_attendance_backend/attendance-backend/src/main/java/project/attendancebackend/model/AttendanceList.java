package project.attendancebackend.model;

public class AttendanceList {

    private String rollNo;
    private String name;
    private Boolean present ;

    public String getRollNo() {
        return this.rollNo;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getStatus() {
        return this.present ;
    }

    public String setRollNo(String rollNo) {
        return this.rollNo = rollNo;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public Boolean setStatus(Boolean present) {
        return this.present = present;
    }

    
}
