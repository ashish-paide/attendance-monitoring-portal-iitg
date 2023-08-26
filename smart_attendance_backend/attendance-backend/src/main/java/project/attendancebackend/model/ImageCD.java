package project.attendancebackend.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageCD {
    
    private MultipartFile file;
    private String classId;
    private String date ;

    public MultipartFile getImage() {
        return this.file;
    }

    public String getClassId() {
        return this.classId;
    }

    public String getDate() {
        return this.date;
    }
    
}
