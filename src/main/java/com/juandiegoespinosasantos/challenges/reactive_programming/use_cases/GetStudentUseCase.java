package com.juandiegoespinosasantos.challenges.reactive_programming.use_cases;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;

import java.util.Optional;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 23, 2023
 * @since 17
 */
public class GetStudentUseCase extends AbstractUseCase<Optional<Student>> {

    private final IStudentService service;
    private final int id;

    public GetStudentUseCase(IStudentService service, int id) {
        this.service = service;
        this.id = id;
    }

    @Override
    protected Optional<Student> process() {
        return service.findById(id);
    }
}