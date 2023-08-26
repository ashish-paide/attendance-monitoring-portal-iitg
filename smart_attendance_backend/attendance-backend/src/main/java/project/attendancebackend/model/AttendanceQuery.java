package project.attendancebackend.model;

import java.util.List;
import java.util.ArrayList;
public class AttendanceQuery {
    private List<String> rollNos = new ArrayList<String>();
    private String classId;
    private String date;

    public String getDate(){
        return this.date;
    }

    public String getClassId(){
        return this.classId;
    }

    public List<String> getRollNos(){
        return this.rollNos;
    }
}
