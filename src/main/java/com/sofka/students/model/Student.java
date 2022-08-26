package com.sofka.students.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.util.annotation.NonNull;

@Data
@Document(collection= "Student")
public class Student {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String surName;

    @NonNull
    private String documentType;

    @NonNull
    private String document;

    @NonNull
    private String address;

    @NonNull
    private String representative;

    @NonNull
    private String phone;

    @NonNull
    private Integer grade;

    @NonNull
    private Integer state;

    /**
     *
     * @param id id generate for mongodb
     * @param name student name type String
     * @param surName student surname type String
     * @param documentType type student's document type String
     * @param document student document type String
     * @param address student address type String
     * @param representative student representative type String
     * @param phone student phone type String
     * @param grade student grade type Integer
     */
    public Student(String id, String name, String surName, String documentType, String document, String address, String representative, String phone, Integer grade) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.documentType = documentType;
        this.document = document;
        this.address = address;
        this.representative = representative;
        this.phone = phone;
        this.grade = grade;
        this.state = 1;
    }

}

