package sysc4806.group7.mini_shopify;

import jakarta.persistence.*;

/**
 * A system user that can log in with username and password.
 */
@Entity
@Table(name = "app_user")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    public User() {};
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getUserName() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [Name: " + name + ", Type: " + getClass().getSimpleName() + "]";
    }
}
