package vn.vti.moneypig.otp;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.PhoneNumberUtils;
//import com.google.firebase.auth.ShortCodeVerificationSession;
//import com.google.firebase.auth.ShortCodeVerificationSessionResult;
//import com.google.firebase.auth.ShortCodeVerificationSessionResult.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OTPService {
    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public OTPService(FirebaseApp firebaseApp) {
        this.firebaseMessaging = FirebaseMessaging.getInstance(firebaseApp);
    }

    public void sendOTP(String registrationToken) {
        String otp = OTPGenerator.generateOTP();
        Message message = Message.builder()
                .putData("title", "OTP Verification")
                .putData("body", "Your OTP: " + otp)
                .setToken(registrationToken)
                .build();
        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            // Handle the exception
        }
    }

//    public void sendOTP(String phoneNumber) {
//        String otp = OTPGenerator.generateOTP();
//
//        try {
//            String formattedPhoneNumber = PhoneNumberUtils.formatNumberToE164(phoneNumber, "your_country_code");
//            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                    formattedPhoneNumber,
//                    60, // Timeout duration in seconds
//                    java.util.concurrent.TimeUnit.SECONDS,
//                    null, // Executor - you can provide your custom executor if needed
//                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                        @Override
//                        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//                            // This method will be triggered if the verification is automatically completed
//                            // You can handle the case when the OTP is automatically verified
//                        }
//
//                        @Override
//                        public void onVerificationFailed(FirebaseAuthException e) {
//                            // Handle the verification failure
//                        }
//
//                        @Override
//                        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                            // This method will be triggered when the OTP code is successfully sent via SMS
//                            // You can send the verificationId and otp to the client-side to handle OTP verification
//                            // For example, you can store the verificationId in a session or database for later use
//                            // and send the otp to the client-side for user input verification
//                        }
//                    }
//            );
//        } catch (FirebaseAuthException e) {
//            // Handle the exception
//        }
//    }
}
