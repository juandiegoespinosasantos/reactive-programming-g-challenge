package com.juandiegoespinosasantos.challenges.reactive_programming.services;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
public interface IStudentService {

    Mono<Student> create(Student entity);

    Mono<Student> edit(Integer id, Student entity);

    Mono<Student> findById(Integer id);

    Flux<Student> findActives();

    Mono<Void> delete(Integer id);
}