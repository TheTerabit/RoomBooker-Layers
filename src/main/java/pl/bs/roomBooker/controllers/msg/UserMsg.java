package pl.bs.roomBooker.controllers.msg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserMsg {

    private final String username;
    private final String name;
    private final String surname;
    private final String password;
    private final String street;
    private final String houseNumber;
    private final String city;
    private final String country;
    private final String email;
    private final String phoneNumber;
    private final String companyName;
    private final String jobTitleName;

    @JsonCreator
    public UserMsg(@JsonProperty("username") String username,
                   @JsonProperty("name") String name,
                   @JsonProperty("surname") String surname,
                   @JsonProperty("password") String password,
                   @JsonProperty("street") String street,
                   @JsonProperty("houseNumber") String houseNumber,
                   @JsonProperty("city") String city,
                   @JsonProperty("country") String country,
                   @JsonProperty("email") String email,
                   @JsonProperty("phoneNumber") String phoneNumber,
                   @JsonProperty("companyName") String companyName,
                   @JsonProperty("jobTitleName") String jobTitleName) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.jobTitleName = jobTitleName;
    }
}