package project.attendancebackend.controller;



import java.io.IOException;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import project.attendancebackend.service.AttendanceService;

@RestController
@RequestMapping(path = "/api")
public class ImageController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping(path = "/uploadImage")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> uploadImage(
        @RequestParam("image") MultipartFile file,
        @RequestParam("classId") String classId,
        @RequestParam("date") String date
        ) throws IOException{

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No image found in the request.");
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        byte[] file_bytes = file.getBytes();
        ByteArrayResource resource = new ByteArrayResource(file_bytes) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        
        body.add("image", resource);
        body.add("classId",classId);
        body.add("date", date);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        
        ResponseEntity<String> response = attendanceService.saveAttendanceData(requestEntity);
        System.out.println(response);
        return ResponseEntity.ok(response.getBody());
    }


}