package backend.sellerB.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

@Component
public class FcmUtil {


    public static void send_FCM(String token, String title, String content) {
        try {
//            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\multicampus\\Documents\\S07P12D105\\BE\\sellerB\\src\\main\\resources\\sellerb-28f08-firebase-adminsdk-szd5u-ff507df489.json");
//            FileInputStream serviceAccount = new FileInputStream("/var/jenkins_home/workspace/sellerB/BE/sellerB/src/main/resources/sellerb-28f08-firebase-adminsdk-szd5u-ff507df489.json");
            PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
//            Resource resource = patternResolver.getResource("/static/sellerb-28f08-firebase-adminsdk-szd5u-ff507df489.json").getInputStream();
            InputStream serviceAccount = patternResolver.getResource("/sellerb-28f08-firebase-adminsdk-szd5u-ff507df489.json").getInputStream();
//			FileInputStream serviceAccount = new FileInputStream("/home/careme/app/WEB-INF/classes/firebase/firebase_service_key.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://sellerb-28f08.firebaseio.com/")
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            String registrationToken = token;

            Message msg = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder()
                            .setTtl(3600 * 1000)
                            .setPriority(AndroidConfig.Priority.NORMAL)
                            .setNotification(AndroidNotification.builder()
                                    .setTitle(title)
                                    .setBody(content)
                                    .setIcon("stock_ticker_update")
                                    .setColor("#f45342")
                                    .build())
                            .build())
                    .setToken(registrationToken)
                    .build();

            String response = FirebaseMessaging.getInstance().send(msg);

            System.out.println("Successfully sent message : " + response);
        } catch (Exception e) {
            // TODO
            System.out.println("메세지 전송 실패");
        }
    }
}
