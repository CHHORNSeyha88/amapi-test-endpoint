package org.homework.amapitestendpoints.controller;

import com.google.api.services.androidmanagement.v1.AndroidManagement;
import com.google.api.services.androidmanagement.v1.model.Enterprise;
import com.google.api.services.androidmanagement.v1.model.SignupUrl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.homework.amapitestendpoints.base.BaseController;
import org.homework.amapitestendpoints.dto.ApiStructureResponse;
import org.homework.amapitestendpoints.dto.EnterprisesResponseDTO;
import org.homework.amapitestendpoints.dto.SignupUrlResponseDTO;
import org.homework.amapitestendpoints.service.AndroidApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Developed by ChhornSeyha
 * Date: 03/10/2025
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/management")
@Tag(name = "Android Management", description = "APIs for interacting with Google's Android Management API")
public class AndroidApiController extends BaseController {

    private final AndroidApiService androidApiService;
    private final AndroidManagement androidManagement;
    @Value("${google.gcp.api.application.projectid}")
    private String projectId;


    @GetMapping("/enterprises")
    @Operation(summary = "List all enterprises", description = "Fetches the list of enterprises from Google Android Management API.")
    public ResponseEntity<ApiStructureResponse<List<EnterprisesResponseDTO>>> getEnterprises() {
        try {
            List<EnterprisesResponseDTO> enterprises = androidApiService.listEnterprisesDTO();
            return response(HttpStatus.OK, "Enterprises fetched successfully", enterprises);
        } catch (IOException e) {
            e.printStackTrace();
            return response(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch enterprises: " + e.getMessage());
        }
    }


    @PostMapping("/signup-urls")
    @Operation(summary = "Create a signup URL", description = "Generates a URL for an admin to sign up their enterprise.")
    public ResponseEntity<ApiStructureResponse<SignupUrlResponseDTO>> createSignupUrl() {
        try {
            SignupUrl signupUrl = androidApiService.createSignupUrl();
            SignupUrlResponseDTO dto = SignupUrlResponseDTO.builder()
                    .name(signupUrl.getName())
                    .url(signupUrl.getUrl())
                    .build();

            return response(HttpStatus.CREATED, "Signup URL created successfully", dto);
        } catch (IOException e) {
            e.printStackTrace();
            return response(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create signup URL: " + e.getMessage());
        }
    }


    @GetMapping("/signup-callback")
    public ResponseEntity<ApiStructureResponse<EnterprisesResponseDTO>> handleSignupCallback(
            @RequestParam("enterpriseToken") String enterpriseToken,
            @RequestParam("signupUrlName") String signupUrlName
    ) {
        try {
            // Step 1: Create the enterprise
            Enterprise enterprise = new Enterprise();

            Enterprise createdEnterprise = androidManagement.enterprises()
                    .create(enterprise)
                    .setEnterpriseToken(enterpriseToken)
                    .setSignupUrlName(signupUrlName)
                    .setProjectId(projectId)
                    .execute();

            // Step 2: Fetch the enterprise again to get Google-normalized displayName
            Enterprise normalizedEnterprise = androidManagement.enterprises()
                    .get(createdEnterprise.getName())
                    .execute();

            // Step 3: Map to DTO
            EnterprisesResponseDTO dto = EnterprisesResponseDTO.builder()
                    .name(normalizedEnterprise.getName())
                    .displayName(normalizedEnterprise.getEnterpriseDisplayName())
                    .build();

            return response(HttpStatus.CREATED, "Enterprise created successfully", dto);

        } catch (IOException e) {
            e.printStackTrace();
            return response(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create enterprise: " + e.getMessage());
        }
    }



}
