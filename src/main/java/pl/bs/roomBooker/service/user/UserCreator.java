package pl.bs.roomBooker.service.user;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.UserMsg;
import pl.bs.roomBooker.models.user.*;

@Service
class UserCreator {

    private final UserDao userDao;
    private JobTitle jobTitle;
    private Company company;
    private User user;
    private UserMsg userMsg;
    private UserPassword userPassword;
    private UserAddress userAddress;
    private UserContact userContact;

    UserCreator(UserDao userDao) {
        this.userDao = userDao;
    }

    void create(UserMsg userMsg) {
        setUserMsg(userMsg);
        createJobTitleIfNotExist();
        createCompanyIfNotExist();
        createUser();
        createUserPassword();
        createUserAddress();
        createUserContact();
        saveUser();
    }

    private void setUserMsg(UserMsg userMsg) {
        this.userMsg = userMsg;
    }

    private void createJobTitleIfNotExist() {
        JobTitle jobTitle = userDao.findJobByName(userMsg.getJobTitleName()); ;
        if(jobTitle == null){
            jobTitle = new JobTitle(userMsg.getJobTitleName());
        }
        this.jobTitle = jobTitle;
    }

    private void createCompanyIfNotExist() {
        Company company = userDao.findCompanyByName(userMsg.getCompanyName());
        if(company == null){
            company = new Company(userMsg.getCompanyName());
        }
        this.company = company;
    }

    private void createUser() {
        User user = new User(userMsg.getUsername(),
                userMsg.getName(),
                userMsg.getSurname());
        user.setCompany(company);
        user.setJobTitle(jobTitle);
        userDao.saveUser(user);
        this.user = user;
    }

    private void createUserPassword() {
        UserPassword userPassword = new UserPassword(user.getId(),
                userMsg.getPassword());
        this.userPassword = userPassword;
    }

    private void createUserAddress() {
        UserAddress userAddress = new UserAddress(user.getId(),
                userMsg.getStreet(),
                userMsg.getHouseNumber(),
                userMsg.getCity(),
                userMsg.getCountry());
        this.userAddress = userAddress;
    }

    private void createUserContact() {
        UserContact userContact = new UserContact(user.getId(),
                userMsg.getEmail(),
                userMsg.getPhoneNumber());
        this.userContact = userContact;
    }

    private void saveUser() {
        userDao.saveJobTitle(jobTitle);
        userDao.saveCompany(company);
        userDao.saveUser(user);
        userDao.saveUserPassword(userPassword);
        userDao.saveUserAddress(userAddress);
        userDao.saveUserContact(userContact);
    }

}
