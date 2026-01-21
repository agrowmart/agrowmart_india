package com.agrowmart.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            // Read Firebase JSON from ENV variable
            String firebaseJson = System.getenv("FIREBASE_CREDENTIALS_JSON");

            if (firebaseJson == null || firebaseJson.isEmpty()) {
                System.out.println("Firebase disabled: FIREBASE_CREDENTIALS_JSON not found");
                return; // ✅ Do NOT crash app
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(
                                    new ByteArrayInputStream(firebaseJson.getBytes(StandardCharsets.UTF_8))
                            )
                    )
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase initialized successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Firebase initialization failed", e);
        }
    }
}
