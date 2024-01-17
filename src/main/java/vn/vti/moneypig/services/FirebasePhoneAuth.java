package vn.vti.moneypig.services;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
public class FirebasePhoneAuth {
    private final FirebaseAuth firebaseAuth;

    public FirebasePhoneAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public String authenticateWithPhoneNumber(String idToken, String phoneNumber) {
        try {
            String uid = firebaseAuth.createCustomToken(phoneNumber);
            FirebaseToken token = firebaseAuth.verifyIdToken(idToken);

            if (token.getUid().equals(uid)) {
                return uid;
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }

        return null;
    }
}
