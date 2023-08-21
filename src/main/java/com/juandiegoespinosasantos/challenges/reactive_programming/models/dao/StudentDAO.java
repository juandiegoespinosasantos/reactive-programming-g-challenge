package com.juandiegoespinosasantos.challenges.reactive_programming.models.dao;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
@Repository
public interface StudentDAO extends R2dbcRepository<Student, Integer> {

    @Query("SELECT * FROM student WHERE active = true")
    Flux<Student> findActives();
}