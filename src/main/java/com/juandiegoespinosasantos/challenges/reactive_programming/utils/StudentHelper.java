package com.juandiegoespinosasantos.challenges.reactive_programming.utils;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.StudentDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.models.entities.Student;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 21, 2023
 * @since 17
 */
public final class StudentHelper {

    private StudentHelper() {
    }

    public static StudentDTO buildDTO(final Student entity) {
        java.sql.Timestamp createdAt = entity.getCreatedAt();
        long ca = (createdAt == null) ? 0L : createdAt.getTime();

        java.sql.Timestamp latestUpdate = entity.getLatestUpdate();
        long lu = (latestUpdate == null) ? 0L : latestUpdate.getTime();

        return new StudentDTO(entity.getId(),
                entity.getNames(),
                entity.getSurnames(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.isActive(),
                ca,
                lu);
    }

    public static Student buildEntity(final StudentDTO dto) {
        return Student.builder()
                .id(dto.id())
                .names(dto.names())
                .surnames(dto.surnames())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .active(dto.active())
                .createdAt(new java.sql.Timestamp(dto.createdAt()))
                .latestUpdate(new java.sql.Timestamp(dto.latestUpdate()))
                .build();
    }
}
