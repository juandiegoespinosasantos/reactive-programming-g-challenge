package com.juandiegoespinosasantos.challenges.reactive_programming.services;

import com.juandiegoespinosasantos.challenges.reactive_programming.models.dao.StudentDAO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.test_utils.StudentTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @Mock
    private StudentDAO mockDao;

    private IStudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService(mockDao);
    }

    @Test
    void createTest() {
        // given
        Student entity = StudentTestUtil.getEntityToCreate();
        Student expected = StudentTestUtil.getCreatedEntity();
        Mockito.when(mockDao.save(entity)).thenReturn(expected);

        // when
        Student actual = service.create(entity);

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void editTest() {
        // given
        Student entity = StudentTestUtil.getEntityToEdit();
        Student expected = StudentTestUtil.getEditedEntity();
        Mockito.when(mockDao.save(entity)).thenReturn(expected);

        // when
        Student actual = service.edit(entity);

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void givenExistentEntityWhenFindByIdThenReturnThatResponse() {
        // given
        Student expected = StudentTestUtil.getEntityWithAllAttrs();
        Integer id = expected.getId();
        Mockito.when(mockDao.findById(id)).thenReturn(Optional.of(expected));

        // when
        Optional<Student> opt = service.findById(id);

        // then
        Assertions.assertNotNull(opt);
        Assertions.assertTrue(opt.isPresent());

        Student actual = opt.get();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void givenNoExistentEntityWhenFindByIdThenReturnEmpty() {
        // given
        Integer id = 1;
        Mockito.when(mockDao.findById(id)).thenReturn(Optional.empty());

        // when
        Optional<Student> opt = service.findById(id);

        // then
        Assertions.assertNotNull(opt);
        Assertions.assertTrue(opt.isEmpty());
    }

    @Test
    void givenActiveStudentsWhenFindActivesThenReturnThatResponse() {
        // given
        List<Student> expected = StudentTestUtil.getActivesList(10);
        Mockito.when(mockDao.findByActive(true)).thenReturn(expected);

        // when
        List<Student> actual = service.findActives();

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertFalse(actual.isEmpty());

        for (Student student : actual) {
            Assertions.assertTrue(student.isActive());
        }
    }

    @Test
    void givenEmptyWhenFindActivesThenReturnThatResponse() {
        // given
        Mockito.when(mockDao.findByActive(true)).thenReturn(Collections.emptyList());

        // when
        List<Student> actual = service.findActives();

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void deleteTest() {
        // given
        int id = 1;

        // when
        service.delete(id);

        // then
        Mockito.verify(mockDao, Mockito.only()).deleteById(id);
    }
}