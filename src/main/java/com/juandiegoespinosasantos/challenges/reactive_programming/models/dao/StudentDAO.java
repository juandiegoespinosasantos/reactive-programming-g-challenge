package com.juandiegoespinosasantos.challenges.reactive_programming.models.dao;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
@Repository
public interface StudentDAO extends CrudRepository<Student, Integer> {
}