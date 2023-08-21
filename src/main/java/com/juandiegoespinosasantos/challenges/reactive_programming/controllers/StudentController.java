package com.juandiegoespinosasantos.challenges.reactive_programming.controllers;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;
import com.juandiegoespinosasantos.challenges.reactive_programming.utils.StudentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<StudentDTO> create(@RequestBody StudentDTO requestBody) {
        Student entity = StudentHelper.buildEntity(requestBody);

        return service.create(entity)
                .map(StudentHelper::buildDTO);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<StudentDTO> edit(@PathVariable("id") Integer id,
                                 @RequestBody StudentDTO requestBody) {
        Student entity = StudentHelper.buildEntity(requestBody);

        return service.edit(id, entity)
                .map(StudentHelper::buildDTO);
    }

    @GetMapping(path = "/{id}")
    public Mono<StudentDTO> findById(@PathVariable("id") Integer id) {
        return service.findById(id)
                .map(StudentHelper::buildDTO);
    }

    @GetMapping(path = "")
    public Flux<StudentDTO> findActives() {
        return service.findActives()
                .map(StudentHelper::buildDTO);
    }

    @DeleteMapping(path = "/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id) {
        return service.delete(id);
    }
}