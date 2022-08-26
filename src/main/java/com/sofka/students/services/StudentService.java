package com.sofka.students.services;


import com.sofka.students.model.Student;
import com.sofka.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Mono<Student> createStudent(Student student){
        return studentRepository.save(student).log();
    }

    public Mono<Student> findStudentById(String id){
        return studentRepository.findById(id).log();
    }

    public Mono<ResponseEntity<Student>> updateStudent(String id, Student student){
        return studentRepository.findById(id)
                .flatMap(old -> {
                    old.setName(student.getName() == null? old.getName():student.getName());
                    old.setSurName(student.getSurName()== null? old.getSurName(): student.getSurName() );
                    old.setDocumentType(student.getDocumentType()== null? old.getDocumentType(): student.getDocumentType() );
                    old.setDocument(student.getDocument()== null? old.getDocument(): student.getDocument());
                    old.setAddress(student.getAddress()== null? old.getAddress(): student.getAddress());
                    old.setRepresentative(student.getRepresentative()== null? old.getRepresentative(): student.getRepresentative());
                    old.setPhone(student.getPhone()== null? old.getPhone(): student.getPhone());
                    old.setGrade(student.getGrade()== null? old.getGrade(): student.getGrade());
                    return studentRepository.save(old);
                })
                .map(updateStudent -> new ResponseEntity<>(updateStudent, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }


    public Mono<Student> logicalDeleteStudent(String id){
        return  studentRepository.findById(id)
                .flatMap(student -> {
                    student.setState(0);
                    return studentRepository.save(student);
                }).log();
    }


    public Flux<Student> getHistoryStudents(){
        return studentRepository.findAll().delayElements(Duration.ofSeconds(2)).log();
    }

    public Mono<Student> physicalDeleteStudent(String id){
        return studentRepository.findById(id)
                .flatMap(removedStudent -> studentRepository.delete(removedStudent)
                .then(Mono.just(removedStudent)));
    }

}





