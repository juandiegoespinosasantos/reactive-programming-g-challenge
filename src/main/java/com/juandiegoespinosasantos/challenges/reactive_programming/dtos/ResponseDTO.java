package com.juandiegoespinosasantos.challenges.reactive_programming.dtos;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 21, 2023
 * @since 17
 */
public record ResponseDTO(String message, Object payload) {

    public ResponseDTO(String message) {
        this(message, null);
    }
}