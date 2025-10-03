package org.homework.amapitestendpoints.config;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.androidmanagement.v1.AndroidManagement;
import com.google.api.services.androidmanagement.v1.AndroidManagementScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

/**
 * Developed by ChhornSeyha
 * Date: 03/10/2025
 */

@Configuration
public class GoogleApiConfig {

    @Value("${google.gcp.api.application.name}")
    private String APPLICATION_NAME;

    @Value("${google.gcp.api.application.credential}")
    private String CREDENTIALS_FILE_PATH;

    @Bean
    public AndroidManagement androidManagement() throws IOException {
        InputStream credentialsStream  = new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream();

        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream)
                .createScoped(Collections.singleton(AndroidManagementScopes.ANDROIDMANAGEMENT));

        return new AndroidManagement.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
