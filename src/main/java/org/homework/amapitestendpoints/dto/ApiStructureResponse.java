package org.homework.amapitestendpoints.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;

/**
 * Developed by ChhornSeyha
 * Date: 07/08/2025
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiStructureResponse<T>{
    private String message;
    private T payload;
    private HttpStatus status;
    private Instant timestamp;
}
