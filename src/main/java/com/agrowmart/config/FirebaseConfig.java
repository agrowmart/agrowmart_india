package com.agrowmart.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            String json = System.getenv("FIREBASE_CREDENTIALS_JSON");
            if (json == null || json.isEmpty()) {
                throw new RuntimeException("Firebase credentials not set in environment!");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(json.getBytes())))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Firebase load failed – check environment variable!", e);
        }
    }
}
