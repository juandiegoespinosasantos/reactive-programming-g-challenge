package com.juandiegoespinosasantos.challenges.reactive_programming.adapters;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;
import com.juandiegoespinosasantos.challenges.reactive_programming.use_cases.CreateStudentUseCase;
import com.juandiegoespinosasantos.challenges.reactive_programming.use_cases.DeleteStudentUseCase;
import com.juandiegoespinosasantos.challenges.reactive_programming.use_cases.GetActiveStudentUseCase;
import com.juandiegoespinosasantos.challenges.reactive_programming.use_cases.GetStudentUseCase;
import com.juandiegoespinosasantos.challenges.reactive_programming.use_cases.UpdateStudentUseCase;
import com.juandiegoespinosasantos.challenges.reactive_programming.utils.StudentHelper;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
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
        return Single.create(source -> {
            try {
                Student created = new CreateStudentUseCase(service, requestBody).execute();
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
        return Completable.create(source -> {
            try {
                new UpdateStudentUseCase(service, id, requestBody).execute();
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
        Optional<Student> opt = new GetStudentUseCase(service, id).execute();
        if (opt.isEmpty()) return Single.never();

        return Single.create(source -> {
            try {
                StudentDTO dto = StudentHelper.buildDTO(opt.get());
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
        List<Student> students = new GetActiveStudentUseCase(service).execute();

        return Observable.fromIterable(students)
                .map(StudentHelper::buildDTO);
    }

    /**
     * Procesa el borrado del estudiante indicado
     *
     * @param id ID del estudiante a eliminar
     * @return Completable confirmando el borrado de la entidad
     */
    public Completable processDelete(final int id) {
        return Completable.create(source -> {
            try {
                new DeleteStudentUseCase(service, id).execute();
                source.onComplete();
            } catch (Exception ex) {
                source.onError(ex);
            }
        });
    }
}