package com.juandiegoespinosasantos.challenges.reactive_programming.services;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import io.reactivex.rxjava3.core.Single;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
public interface IStudentService {

    Single<Student> create(Student entity);
}