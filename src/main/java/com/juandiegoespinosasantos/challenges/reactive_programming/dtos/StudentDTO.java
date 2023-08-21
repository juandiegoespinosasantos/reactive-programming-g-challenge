package com.juandiegoespinosasantos.challenges.reactive_programming.dtos;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 19, 2023
 * @since 17
 */
public record StudentDTO(Integer id,
                         String names,
                         String surnames,
                         String email,
                         String phoneNumber,
                         boolean active,
                         long createdAt,
                         long latestUpdate) {
}