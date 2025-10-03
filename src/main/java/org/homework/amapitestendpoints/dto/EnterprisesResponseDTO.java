package org.homework.amapitestendpoints.dto;

import com.google.api.services.androidmanagement.v1.model.Enterprise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

import java.util.List;

/**
 * Developed by ChhornSeyha
 * Date: 03/10/2025
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnterprisesResponseDTO {
    private String name;
    private String displayName;

}
