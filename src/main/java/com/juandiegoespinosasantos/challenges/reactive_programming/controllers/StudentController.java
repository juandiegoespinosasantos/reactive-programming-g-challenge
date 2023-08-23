package com.juandiegoespinosasantos.challenges.reactive_programming.controllers;

import com.juandiegoespinosasantos.challenges.reactive_programming.adapters.StudentAdapter;
import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.ResponseDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
@RestController
@RequestMapping("/v1/students")
@CrossOrigin
public class StudentController {

    private final StudentAdapter adapter;

    @Autowired
    public StudentController(StudentAdapter adapter) {
        this.adapter = adapter;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<ResponseDTO>> create(@RequestBody StudentDTO requestBody) {
        return adapter.processCreate(requestBody)
                .subscribeOn(Schedulers.io())
                .map(single -> {
                    ResponseDTO response = new ResponseDTO("Estudiante creado exitosamente", single);
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                });
    }
}