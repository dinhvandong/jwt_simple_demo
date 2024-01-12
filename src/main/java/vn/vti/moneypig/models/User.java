package vn.vti.moneypig.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "users")
public class User {
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    private String username;
    private String email;
    private String password;
    private String googleId;
    private String phone;
    private int status;

    public User(Long id, String username, String email, String password, String googleId, String phone, int status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.googleId = googleId;
        this.phone = phone;
        this.status = status;
    }

    public User(String username, String password) {
        this.username = username;
        this.password  = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", googleId='" + googleId + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}