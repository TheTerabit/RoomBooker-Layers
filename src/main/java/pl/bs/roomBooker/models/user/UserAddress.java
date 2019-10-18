package pl.bs.roomBooker.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAddress {

    @Id
    @Column(name = "id")
    private Long id;
    private String street;
    private String number;
    private String city;
    private String country;

    public UserAddress() {
    }

    public UserAddress(Long id, String street, String number, String city, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}