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
        return studentRepository.findAll()
                .filter(student -> student.getState() == 1)
                .delayElements(Duration.ofSeconds(1)).log();
    }

    public Flux<Student> getHistoryStudents(){
        return studentRepository.findAll().delayElements(Duration.ofSeconds(2)).log();
    }


    public Mono<Student> createStudent(Student student){
        return studentRepository.save(student).log();
    }

    public Mono<Student> findStudentById(String id){

        return studentRepository.findById(id).log();
    }

    public Mono<Student> logicalDeleteStudent(String id){
        return  studentRepository.findById(id)
                .flatMap(student -> {
                    student.setState(0);
                    return studentRepository.save(student);
                }).log();
    }

    public Mono<Student> physicalDeleteStudent(String id){
        return studentRepository.findById(id)
                .flatMap(removedStudent -> studentRepository.delete(removedStudent)
                .then(Mono.just(removedStudent)));
    }

}





