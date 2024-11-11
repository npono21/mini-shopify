package SYSC4806Project;

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
    private String password;

    public User() {};
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.name.equals(username) && this.password.equals(password);
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
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
