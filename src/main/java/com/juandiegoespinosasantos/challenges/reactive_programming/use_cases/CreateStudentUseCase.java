package com.juandiegoespinosasantos.challenges.reactive_programming.use_cases;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;
import com.juandiegoespinosasantos.challenges.reactive_programming.utils.StudentHelper;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 23, 2023
 * @since 17
 */
public class CreateStudentUseCase extends AbstractUseCase<Student> {

    private final IStudentService service;
    private final StudentDTO requestBody;

    public CreateStudentUseCase(IStudentService service,
                                StudentDTO requestBody) {
        this.service = service;
        this.requestBody = requestBody;
    }

    @Override
    protected Student process() {
        Student student = StudentHelper.buildEntity(requestBody);

        return service.create(student);
    }
}