package com.juandiegoespinosasantos.challenges.reactive_programming.controllers;

import com.juandiegoespinosasantos.challenges.reactive_programming.dtos.ResponseDTO;
import com.juandiegoespinosasantos.challenges.reactive_programming.exceptions.StudentException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 26, 2023
 * @since 17
 */
@ControllerAdvice
@Log4j2
public class ControllerExceptionHandler {

    @ExceptionHandler(StudentException.class)
    public ResponseEntity<ResponseDTO> studentExceptionHandler(StudentException ex) {
        return getResponse(ex, ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> exceptionHandler(Exception ex) {
        return getResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error");
    }

    private ResponseEntity<ResponseDTO> getResponse(final Exception ex, final HttpStatus httpStatus, final String message) {
        log.error(ex.getMessage(), ex);
        ResponseDTO response = new ResponseDTO(message);

        return ResponseEntity.status(httpStatus).body(response);
    }
}