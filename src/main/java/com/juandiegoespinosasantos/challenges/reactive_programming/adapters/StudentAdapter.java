package com.juandiegoespinosasantos.challenges.reactive_programming.adapters;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.exceptions.ClientException;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;
import com.juandiegoespinosasantos.challenges.reactive_programming.utils.StudentHelper;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 22, 2023
 * @since 17
 */
@Component
public class StudentAdapter {

    private final IStudentService service;

    @Autowired
    public StudentAdapter(IStudentService service) {
        this.service = service;
    }

    /**
     * Procesa la creación de un estudiante
     *
     * @param requestBody Objeto de solicitud
     * @return Single con DTO de la entidad creada
     */
    public Single<StudentDTO> processCreate(final StudentDTO requestBody) {
        Student student = StudentHelper.buildEntity(requestBody);

        return Single.create(source -> {
            try {
                Student created = service.create(student);
                StudentDTO dto = StudentHelper.buildDTO(created);
                source.onSuccess(dto);
            } catch (Exception ex) {
                source.onError(ex);
            }
        });
    }

    /**
     * Procesa la actualización del estudiante indicado
     *
     * @param id          ID del estudiante a actualizar
     * @param requestBody Objeto de solicitud
     * @return Completable confirmando la actualización de la entidad
     */
    public Completable processUpdate(final int id, final StudentDTO requestBody) {
        Optional<Student> opt = service.findById(id);

        if (opt.isEmpty()) {
            return Completable.error(new ClientException(HttpStatus.NOT_FOUND, "Estudiante [" + id + "] no registrado"));
        }

        return Completable.create(source -> {
            try {
                service.edit(opt.get());
                source.onComplete();
            } catch (Exception ex) {
                source.onError(ex);
            }
        });
    }

    /**
     * Obtiene un Single del estudiante consultado
     *
     * @param id ID del estudiante a consultar
     * @return Single con DTO de entidad consultada
     */
    public Single<StudentDTO> processFindById(final int id) {
        Optional<Student> opt = service.findById(id);
        if (opt.isEmpty()) return Single.never();

        StudentDTO dto = StudentHelper.buildDTO(opt.get());

        return Single.create(source -> {
            try {
                source.onSuccess(dto);
            } catch (Exception ex) {
                source.onError(ex);
            }
        });
    }

    /**
     * Procesa la consulta de todos los estudiantes en estado activo
     *
     * @return Observable de los estudiantes consultados
     */
    public Observable<StudentDTO> processFindActives() {
        List<Student> students = service.findActives();

        return Observable.fromIterable(students)
                .map(StudentHelper::buildDTO);
    }
}