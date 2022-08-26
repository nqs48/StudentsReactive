package com.sofka.students.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection= "Student")
public class Student {

    @Id
    private String id;
    private String name;
    private String surName;
    private String documentType;
    private String document;
    private String address;
    private String representative;
    private String phone;
    private Integer grade;
    private Integer state;

    public Student(String id, String name, String surName, String documentType, String document, String address, String representative, String phone, Integer grade, Integer state) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.documentType = documentType;
        this.document = document;
        this.address = address;
        this.representative = representative;
        this.phone = phone;
        this.grade = grade;
        this.state = state;
    }


    public Student(String name, String surName, String documentType, String document) {
        this.name = name;
        this.surName = surName;
        this.documentType = documentType;
        this.document = document;
    }
}

