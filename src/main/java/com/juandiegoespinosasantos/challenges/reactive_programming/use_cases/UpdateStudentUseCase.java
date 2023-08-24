package com.juandiegoespinosasantos.challenges.reactive_programming.use_cases;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.exceptions.ClientException;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;
import com.juandiegoespinosasantos.challenges.reactive_programming.utils.StudentHelper;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 23, 2023
 * @since 17
 */
public class UpdateStudentUseCase extends AbstractUseCase<Student> {

    private final IStudentService service;
    private final int id;
    private final StudentDTO requestBody;

    private Student pivot;

    public UpdateStudentUseCase(IStudentService service,
                                int id,
                                StudentDTO requestBody) {
        this.service = service;
        this.id = id;
        this.requestBody = requestBody;
    }

    @Override
    protected void validate() throws Exception {
        Optional<Student> opt = service.findById(id);

        if (opt.isEmpty()) {
            throw new ClientException(HttpStatus.NOT_FOUND, "Estudiante [" + id + "] no registrado");
        }

        pivot = opt.get();
    }

    @Override
    protected Student process() {
        Student student = StudentHelper.buildEntity(requestBody);
        student.setId(pivot.getId());
        student.setCreatedAt(pivot.getCreatedAt());

        return service.edit(student);
    }
}