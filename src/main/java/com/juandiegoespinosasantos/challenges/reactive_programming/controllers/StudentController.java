package com.juandiegoespinosasantos.challenges.reactive_programming.controllers;

import com.juandiegoespinosasantos.challenges.reactive_programming.adapters.StudentAdapter;
import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.ResponseDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> update(@PathVariable("id") int id,
                                              @RequestBody StudentDTO requestBody) {
        adapter.processUpdate(id, requestBody)
                .subscribeOn(Schedulers.io());
        ResponseDTO response = new ResponseDTO("Estudiante actualizado exitosamente", requestBody);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<ResponseDTO>> findOne(@PathVariable("id") int id) {
        return adapter.processFindById(id)
                .subscribeOn(Schedulers.io())
                .map(single -> {
                    ResponseDTO response = new ResponseDTO("Consulta realizada", single);
                    return ResponseEntity.ok(response);
                });
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Observable<ResponseEntity<ResponseDTO>> findActives() {
        return adapter.processFindActives()
                .subscribeOn(Schedulers.io())
                .map(single -> {
                    ResponseDTO response = new ResponseDTO("Registros encontrados: ", single);
                    return ResponseEntity.ok(response);
                });
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") int id) {
        adapter.processDelete(id)
                .subscribeOn(Schedulers.io());
        ResponseDTO response = new ResponseDTO("Estudiante [" + id + "] eliminado exitosamente");

        return ResponseEntity.ok(response);
    }
}