package com.hypeform.hypeformTest.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseInitializer {

    @PostConstruct
    private void initFirestore() throws IOException {

      InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("private-key-firestore.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://hypeformtest.firebaseio.com/")
                .build();

        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }


    }

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }


}
