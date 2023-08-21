package com.juandiegoespinosasantos.challenges.reactive_programming.utils;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;
import com.juandiegoespinosasantos.challenges.reactive_programming.test_utils.StudentTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentHelperTest {

    @Test
    void buildDTOTest() {
        Student entity = StudentTestUtil.getEntityWithAllAttrs();
        StudentDTO dto = StudentHelper.buildDTO(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(entity.getId(), dto.id());
        Assertions.assertEquals(entity.getNames(), dto.names());
        Assertions.assertEquals(entity.getSurnames(), dto.surnames());
        Assertions.assertEquals(entity.getEmail(), dto.email());
        Assertions.assertEquals(entity.getPhoneNumber(), dto.phoneNumber());
        Assertions.assertEquals(entity.isActive(), dto.active());
        Assertions.assertEquals(entity.getCreatedAt().toEpochMilli(), dto.createdAt());
        Assertions.assertEquals(entity.getLatestUpdate().toEpochMilli(), dto.latestUpdate());
    }

    @Test
    void buildEntityTest() {
        StudentDTO dto = StudentTestUtil.getDTOWithAllAttrs();
        Student entity = StudentHelper.buildEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.id(), entity.getId());
        Assertions.assertEquals(dto.names(), entity.getNames());
        Assertions.assertEquals(dto.surnames(), entity.getSurnames());
        Assertions.assertEquals(dto.email(), entity.getEmail());
        Assertions.assertEquals(dto.phoneNumber(), entity.getPhoneNumber());
        Assertions.assertEquals(dto.active(), entity.isActive());
        Assertions.assertEquals(dto.createdAt(), entity.getCreatedAt().toEpochMilli());
        Assertions.assertEquals(dto.latestUpdate(), entity.getLatestUpdate().toEpochMilli());
    }
}