package org.homework.amapitestendpoints.service;

import com.google.api.services.androidmanagement.v1.AndroidManagement;
import com.google.api.services.androidmanagement.v1.model.Enterprise;
import com.google.api.services.androidmanagement.v1.model.ListEnterprisesResponse;
import com.google.api.services.androidmanagement.v1.model.SignupUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.amapitestendpoints.dto.EnterprisesResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Developed by ChhornSeyha
 * Date: 03/10/2025
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AndroidApiService {
    private final AndroidManagement androidManagement;
//    ដាក់ GCP Project ID
    @Value("${google.gcp.api.application.projectid}")
    private String projectId;


//    ហៅ API របស់Google
    public ListEnterprisesResponse listEnterprisesResponse()throws IOException{
        return androidManagement.enterprises().list().setProjectId(projectId).execute();
    }


    public List<EnterprisesResponseDTO> listEnterprisesDTO() throws IOException {
        ListEnterprisesResponse response = listEnterprisesResponse(); //ប្រើmethod ខាងលើ
        if (response.getEnterprises() == null) {
            return Collections.emptyList();
        }

        return response.getEnterprises().stream()
                .map(e -> EnterprisesResponseDTO.builder()
                        .name(e.getName())
                        .displayName(e.getEnterpriseDisplayName())

                        .build())
                .collect(Collectors.toList());
    }


    public SignupUrl createSignupUrl() throws IOException {

        // This call generates the signup URL using only the required parameters.
        return androidManagement.signupUrls()
                .create()
                .setProjectId(projectId)
                .setCallbackUrl("https://localhost:8080/signup-callback")
                .execute();
    }


}
