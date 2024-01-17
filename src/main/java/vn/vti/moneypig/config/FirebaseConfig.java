package vn.vti.moneypig.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    private static final String SERVICE_ACCOUNT_KEY_PATH = "serviceAccountKey.json";

    @PostConstruct
    public void initialize() throws IOException {

        ClassPathResource resource = new ClassPathResource(SERVICE_ACCOUNT_KEY_PATH);

        FileInputStream serviceAccount =
                new FileInputStream(resource.getFile()); // Path to your Firebase Admin SDK service account file

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseAuth firebaseAuth() {
        return FirebaseAuth.getInstance();
    }
}