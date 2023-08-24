package com.juandiegoespinosasantos.challenges.reactive_programming.use_cases;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;

import java.util.List;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 23, 2023
 * @since 17
 */
public class GetActiveStudentUseCase extends AbstractUseCase<List<Student>> {

    private final IStudentService service;

    public GetActiveStudentUseCase(IStudentService service) {
        this.service = service;
    }

    @Override
    protected List<Student> process() {
        return service.findActives();
    }
}