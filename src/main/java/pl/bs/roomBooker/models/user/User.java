package pl.bs.roomBooker.models.user;

import pl.bs.roomBooker.models.reservation.Reservation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @SequenceGenerator(name = "myUserGen", sequenceName = "myUserSeq", initialValue = 0, allocationSize = 100)
    @GeneratedValue(generator = "myUserGen")
    @Column(name = "id")
    private Long id;
    @Column(unique=true, name = "username")
    private String username;
    private String name;
    private String surname;
    @OneToOne
    @JoinColumn(name="id", referencedColumnName = "id")
    private UserPassword userPassword;
    @OneToOne
    @JoinColumn(name="id", referencedColumnName = "id")
    private UserAddress userAddress;
    @OneToOne
    @JoinColumn(name="id", referencedColumnName = "id")
    private UserContact userContact;

    @ManyToOne
    @JoinColumn(name = "comapnyId")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "jobTitleId")
    private JobTitle jobTitle;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="username", referencedColumnName = "username")
    private List<Reservation> reservations;


    public User(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public UserContact getUserContact() {
        return userContact;
    }

    public Company getCompany() {
        return company;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserContact(UserContact userContact) {
        this.userContact = userContact;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
/*
Users
-	userId
-	name
-	surname
-	companyId
-	jobTitleId


passwords
-	userId
-	password
userAddresses
- userId
- street
- numer
- City
- country

userContacts
- userId
- userEmail
- userPhoneNumber


Companies
- companyId
- companyName

Jobtitles
- jobTitleId
- jobTitleName

 */
