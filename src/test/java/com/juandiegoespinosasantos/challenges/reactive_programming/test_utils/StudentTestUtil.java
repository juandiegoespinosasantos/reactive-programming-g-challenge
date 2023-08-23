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
        java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

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

    public static StudentDTO getDTOFromCreate() {
        return new StudentDTO(
                0,
                "JOHN",
                "DOE",
                "johndoe@example.com",
                "555-0001",
                true,
                0L,
                0L);
    }

    public static Student getEntityToCreate() {
        return Student.builder()
                .names("JOHN")
                .surnames("DOE")
                .email("johndoe@example.com")
                .phoneNumber("555-0001")
                .build();
    }

    public static Student getCreatedEntity() {
        java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

        return Student.builder()
                .id(1)
                .names("JOHN")
                .surnames("DOE")
                .email("johndoe@example.com")
                .phoneNumber("555-0001")
                .active(true)
                .createdAt(now)
                .latestUpdate(now)
                .build();
    }

    public static StudentDTO getDTOToEdit() {
        return new StudentDTO(
                1,
                "JOHN B.",
                "DOE",
                "jdoe@example.com",
                "555-0003",
                true,
                0L,
                0L);
    }

    public static Student getEntityToEdit() {
        long now = System.currentTimeMillis();
        java.sql.Timestamp createdAt = new java.sql.Timestamp(now - (60L * 60L * 24L * 7L * 1_000L));

        return Student.builder()
                .id(1)
                .names("JOHN B.")
                .surnames("DOE")
                .email("jdoe@example.com")
                .phoneNumber("555-0003")
                .active(true)
                .createdAt(createdAt)
                .build();
    }

    public static Student getEditedEntity() {
        long now = System.currentTimeMillis();
        java.sql.Timestamp createdAt = new java.sql.Timestamp(now - (60L * 60L * 24L * 7L * 1_000L));
        java.sql.Timestamp latestUpdate = new java.sql.Timestamp(now - (60L * 60L * 24L * 7L * 1_000L));

        return Student.builder()
                .id(1)
                .names("JOHN B.")
                .surnames("DOE")
                .email("jdoe@example.com")
                .phoneNumber("555-0003")
                .active(true)
                .createdAt(createdAt)
                .latestUpdate(latestUpdate)
                .build();
    }
}
