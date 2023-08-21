package com.juandiegoespinosasantos.challenges.reactive_programming.test_utils;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 21, 2023
 * @since 17
 */
public final class StudentTestUtil {

    private StudentTestUtil() {
    }

    public static Student getEntityWithAllAttrs() {
        java.time.Instant now = java.time.Instant.now();

        return Student.builder()
                .id(1_000)
                .names("JOHN")
                .surnames("DOE")
                .email("johndoe@example.com")
                .phoneNumber("555-0001")
                .active(true)
                .createdAt(now)
                .latestUpdate(now)
                .build();
    }

    public static StudentDTO getDTOWithAllAttrs() {
        long now = System.currentTimeMillis();

        return new StudentDTO(
                1_000,
                "JOHN",
                "DOE",
                "johndoe@example.com",
                "555-0001",
                true,
                now,
                now);
    }
}
