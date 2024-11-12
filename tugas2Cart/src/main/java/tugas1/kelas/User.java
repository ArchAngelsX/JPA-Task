package tugas1.kelas;

import javax.persistence.*;

@Entity
@Table(name = "users_tbl")
public class User {
    @Id
    private String username;
    private String password;
    private String admin;

    // Getters and Setters

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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}