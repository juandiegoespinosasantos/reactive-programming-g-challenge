package com.juandiegoespinosasantos.challenges.reactive_programming.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 22, 2023
 * @since 17
 */
public class ClientException extends Exception {

    private HttpStatus httpStatus;
    private String message;

    public ClientException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}