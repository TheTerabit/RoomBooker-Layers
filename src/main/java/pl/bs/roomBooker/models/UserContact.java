package pl.bs.roomBooker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserContact {

    @Id
    @Column(name = "id")
    private Long id;
    private String email;
    private String phoneNumber;

    public UserContact() {
    }

    public UserContact(Long id, String email, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
