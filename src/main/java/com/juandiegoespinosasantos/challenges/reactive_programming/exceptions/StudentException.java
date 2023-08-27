package com.juandiegoespinosasantos.challenges.reactive_programming.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 22, 2023
 * @since 17
 */
@Data
public class StudentException extends Exception {

    private HttpStatus httpStatus;
    private String message;

    public StudentException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}