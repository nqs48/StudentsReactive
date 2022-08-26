package com.sofka.students.services;


import com.sofka.students.model.Student;
import com.sofka.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository= studentRepository;
    }

    public Flux<Student> getAllStudents(){
        return studentRepository.findAll().delayElements(Duration.ofSeconds(2)).log();
    }

    public Mono<Student> createStudent(Student student){
        return studentRepository.save(student).log();
    }


}
