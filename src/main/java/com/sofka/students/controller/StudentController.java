package com.sofka.students.controller;


import com.sofka.students.model.Student;
import com.sofka.students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService= studentService;
    }

    @GetMapping(value="/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllBooks(){
        return studentService.getAllStudents();
    }

    @PostMapping(value="/")
    public Mono<Student> createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

}
