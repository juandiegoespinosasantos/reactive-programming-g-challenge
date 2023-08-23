package com.juandiegoespinosasantos.challenges.reactive_programming.services;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;

import java.util.List;
import java.util.Optional;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
public interface IStudentService {

    Student create(Student entity);

    Student edit(Student entity);

    Optional<Student> findById(int id);

    List<Student> findActives();

    void delete(int id);
}