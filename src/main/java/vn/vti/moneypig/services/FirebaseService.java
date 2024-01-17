package vn.vti.moneypig.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

@Service
public class FirebaseService {

    private final FirebaseAuth firebaseAuth;
    private final PhoneNumberUtil phoneNumberUtil;

    @Autowired
    public FirebaseService(FirebaseAuth firebaseAuth, PhoneNumberUtil phoneNumberUtil) {
        this.firebaseAuth = firebaseAuth;
        this.phoneNumberUtil = phoneNumberUtil;
    }

    public FirebaseToken verifyOTP(String idToken) throws FirebaseAuthException {
        return firebaseAuth.verifyIdToken(idToken);
    }

    public void sendOTP(String phoneNumber) throws FirebaseAuthException, NumberParseException {
        String formattedPhoneNumber = normalizePhoneNumber(phoneNumber);
        UserRecord userRecord = firebaseAuth.getUserByPhoneNumber(formattedPhoneNumber);
        String uid = userRecord.getUid();

        // Send the OTP using the Firebase Admin SDK or a third-party library
        // You can use the user's UID and the provided phone number to send the OTP
        // Example: Send an SMS OTP using a third-party library
        // smsOtpLibrary.sendOTP(formattedPhoneNumber, uid);
    }

    private String normalizePhoneNumber(String phoneNumber) throws NumberParseException {
        Phonenumber.PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, "VN");
        System.out.println("PhoneNumber:"+ parsedPhoneNumber.getNationalNumber());
        return phoneNumberUtil.format(parsedPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

//    public void sendOTP(String phoneNumber) {
//        try {
//            String formattedPhoneNumber = PhoneNumberUtils.formatNumberToE164(phoneNumber, "your_country_code");
//
//            // Start the short code verification session
//            ShortCodeVerificationSession session = firebaseAuth.getShortCodeVerificationSession();
//            ShortCodeVerificationSessionResult sessionResult = session.createSession(formattedPhoneNumber);
//            if (sessionResult.getStatus() == Status.SUCCESS) {
//                // Send the OTP code to the phone number
//                String verificationCode = sessionResult.getVerificationCode();
//                // Implement your SMS sending logic here, using the verificationCode and formattedPhoneNumber
//            } else {
//                // Handle the session creation failure
//            }
//        } catch (FirebaseAuthException e) {
//            // Handle the exception
//        }
//    }
}