package com.juandiegoespinosasantos.challenges.reactive_programming.services;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.dao.StudentDAO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

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
    public Mono<Student> create(final Student entity) {
        java.time.Instant now = java.time.Instant.now();
        entity.setCreatedAt(now);
        entity.setLatestUpdate(now);
        entity.setActive(true);

        return dao.save(entity);
    }

    @Override
    public Mono<Student> edit(final Integer id, final Student entity) {
        return dao.findById(id)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(opt -> {
                    if (opt.isEmpty()) return Mono.empty();

                    Student pivot = opt.get();
                    entity.setId(pivot.getId());
                    entity.setCreatedAt(pivot.getCreatedAt());
                    entity.setLatestUpdate(java.time.Instant.now());

                    return dao.save(entity);
                });
    }

    @Override
    public Mono<Student> findById(final Integer id) {
        return dao.findById(id);
    }

    @Override
    public Flux<Student> findActives() {
        return dao.findActives();
    }

    @Override
    public Mono<Void> delete(final Integer id) {
        return dao.deleteById(id);
    }
}