package com.juandiegoespinosasantos.challenges.reactive_programming.use_cases;

import com.juandiegoespinosasantos.challenges.reactive_programming.exceptions.ClientException;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 23, 2023
 * @since 17
 */
public class DeleteStudentUseCase extends AbstractUseCase<Student> {

    private final IStudentService service;
    private final int id;

    private Student student;

    public DeleteStudentUseCase(IStudentService service, int id) {
        this.service = service;
        this.id = id;
    }

    @Override
    protected void validate() throws Exception {
        Optional<Student> opt = service.findById(id);
        if (opt.isEmpty()) throw new ClientException(HttpStatus.NOT_FOUND, "Estudiante [" + id + "] no registrado");
        student = opt.get();
    }

    @Override
    protected Student process() {
        service.delete(student.getId());

        return student;
    }
}