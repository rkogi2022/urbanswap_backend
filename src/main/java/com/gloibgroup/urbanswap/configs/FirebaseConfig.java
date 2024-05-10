package com.gloibgroup.urbanswap.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {
        InputStream serviceAccountStream = FirebaseConfig.class.getResourceAsStream("/firebaseServiceAccountKey.json");

        if (serviceAccountStream == null) {
            throw new IOException("Failed to load service account key file");
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        // Initialize Firebase only if it has not been initialized yet
        if (FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        }

        return FirebaseApp.getInstance(); // Return the existing FirebaseApp instance
    }
}
