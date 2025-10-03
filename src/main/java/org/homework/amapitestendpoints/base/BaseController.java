package org.homework.amapitestendpoints.base;

import org.homework.amapitestendpoints.dto.ApiStructureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

/**
 * Developed by ChhornSeyha
 * Date: 15/08/2025
 */

public class BaseController {

//Builds a success response with a payload.
    protected <T>ResponseEntity<ApiStructureResponse<T>> response (HttpStatus status, String message, T data){
        ApiStructureResponse<T> body = ApiStructureResponse.<T>builder()
                .message(message)
                .payload(data)
                .status(status)
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(body,status);
    }


    //Builds a success response without a payload like for delete operation.
    protected <T> ResponseEntity<ApiStructureResponse<T>> response(HttpStatus httpStatus, String message) {
        ApiStructureResponse<T> body = ApiStructureResponse.<T>builder()
                .message(message)
                .status(httpStatus)
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(body, httpStatus);
    }
}
