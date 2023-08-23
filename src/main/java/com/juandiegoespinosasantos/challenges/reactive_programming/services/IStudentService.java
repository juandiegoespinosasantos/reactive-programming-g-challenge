package com.juandiegoespinosasantos.challenges.reactive_programming.services;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

import java.util.Optional;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
public interface IStudentService {

    Student create(Student entity);

    Completable edit(Student entity);

    Optional<Student> findById(int id);

    Observable<Student> findActives();

    Completable delete(int id);
}