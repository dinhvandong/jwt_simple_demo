package vn.vti.moneypig.dto;

 public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
     public String getEmail() {
         return email;
     }
     public void setEmail(String email) {
         this.email = email;
     }
     public String getPhone() {
         return phone;
     }
     public void setPhone(String phone) {
         this.phone = phone;
     }
     @Override
     public String toString() {
         return "UserDTO{" +
                 "username='" + username + '\'' +
                 ", password='" + password + '\'' +
                 '}';
     }
     public UserDTO(String username, String password, String email, String password1, String phone) {
         this.username = username;
         this.password = password;
         this.email = email;
         this.password = password1;
         this.phone = phone;
     }

     public UserDTO(String username, String password) {
         this.username = username;
         this.password = password;
     }

     public UserDTO() {
     }

     public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}