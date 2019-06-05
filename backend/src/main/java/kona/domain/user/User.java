package kona.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() { /* for hibernate */}

    @Version
    private long version = 0L;

    @Id
    @GeneratedValue
    private long id;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
