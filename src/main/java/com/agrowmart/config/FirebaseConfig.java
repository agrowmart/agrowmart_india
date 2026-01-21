package com.agrowmart.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.util.Base64;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.credentials.json:}")
    private String firebaseBase64;

    @PostConstruct
    public void initialize() {
        try {
            if (firebaseBase64 == null || firebaseBase64.isBlank()) {
                System.out.println("⚠ Firebase disabled: no credentials provided");
                return;
            }

            byte[] decoded = Base64.getDecoder().decode(firebaseBase64);
            ByteArrayInputStream stream = new ByteArrayInputStream(decoded);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase initialized successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Firebase initialization failed", e);
        }
    }
}
