package balu.sbms.studentservice.controller;

import balu.sbms.studentservice.request.StudentRequest;
import balu.sbms.studentservice.response.StudentResponse;
import balu.sbms.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public StudentResponse createStudent(
            @RequestBody StudentRequest studentRequest){
        return studentService.createStudentResponse(studentRequest);
    }
//    public HttpStatus createStudent(@RequestBody StudentRequest studentRequest){
//        studentService.createStudentResponse(studentRequest);
//        return HttpStatus.CREATED;
//    }

    @GetMapping("/getById/{id}")
    public StudentResponse getStudentById(
            @PathVariable long id){
        return studentService.getById(id);
    }
}
