package com.juandiegoespinosasantos.challenges.reactive_programming.controllers;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.ResponseDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;
import com.juandiegoespinosasantos.challenges.reactive_programming.utils.StudentHelper;
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

    private final IStudentService service;

    @Autowired
    public StudentController(IStudentService service) {
        this.service = service;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<ResponseDTO>> create(@RequestBody StudentDTO requestBody) {
        Student entity = StudentHelper.buildEntity(requestBody);

        return service.create(entity)
                .subscribeOn(Schedulers.io())
                .map(single -> {
                    StudentDTO payload = StudentHelper.buildDTO(single);

                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO("Estudiante creado exitosamente", payload));
                });
    }
}