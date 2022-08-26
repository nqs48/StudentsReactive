package com.sofka.students.controller;


import com.sofka.students.model.Student;
import com.sofka.students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Flux<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping(value="/")
    public Mono<Student> createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping(value="/{id}")
    public Mono<Student> findStudentById(@PathVariable String id){
        return studentService.findStudentById(id);
    }

    @PutMapping(value="/{id}")
    public Mono<ResponseEntity<Student>> updateAllBook(@PathVariable String id, @RequestBody Student student ){
        return studentService.updateStudent(id,student);
    }

    @DeleteMapping(value="/{id}")
    public Mono<ResponseEntity<Void>> logicalDeleteStudent(@PathVariable String id){
        return studentService.logicalDeleteStudent(id)
                .map(res -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @DeleteMapping(value="physicalDelete/{id}")
    public Mono<ResponseEntity<Void>> physicalDeleteStudent(@PathVariable String id){
        return studentService.physicalDeleteStudent(id)
                .map(res -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value="history/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getHistoryStudents(){
        return studentService.getHistoryStudents();
    }

}
