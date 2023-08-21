package com.juandiegoespinosasantos.challenges.reactive_programming.services;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.dao.StudentDAO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
@Service
public class StudentService implements IStudentService {

    private final StudentDAO dao;

    @Autowired
    public StudentService(StudentDAO dao) {
        this.dao = dao;
    }

    @Override
    public Single<Student> create(Student entity) {
        java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        entity.setCreatedAt(now);
        entity.setLatestUpdate(now);
        entity.setActive(true);

        return Single.create(source -> {
            try {
                Student student = dao.save(entity);
                source.onSuccess(student);
            } catch (Exception ex) {
                source.onError(ex);
            }
        });
    }
}