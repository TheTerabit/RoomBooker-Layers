package pl.bs.roomBooker.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserPassword {

    @Id
    @Column(name = "id")
    private Long id;
    private String password;

    public UserPassword(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserPassword() {
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
