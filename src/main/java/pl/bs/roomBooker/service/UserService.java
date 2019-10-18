package pl.bs.roomBooker.service;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.UserMsg;
import pl.bs.roomBooker.models.user.*;
import pl.bs.roomBooker.repository.user.*;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserPasswordRepository userPasswordRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserContactRepository userContactRepository;
    private final CompanyRepository companyRepository;
    private final JobTitleRepository jobTitleRepository;

    public UserService(UserRepository userRepository,
                       UserPasswordRepository userPasswordRepository,
                       UserAddressRepository userAddressRepository,
                       UserContactRepository userContactRepository,
                       CompanyRepository companyRepository,
                       JobTitleRepository jobTitleRepository) {
        this.userRepository = userRepository;
        this.userPasswordRepository = userPasswordRepository;
        this.userAddressRepository = userAddressRepository;
        this.userContactRepository = userContactRepository;
        this.companyRepository = companyRepository;
        this.jobTitleRepository = jobTitleRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundUserException(String.format("User %s does not exist", id)));
    }

    public void create(UserMsg userMsg) {
        //if()
        JobTitle jobTitle = jobTitleRepository.findByJobName(userMsg.getJobTitleName());
        if(jobTitle == null){
            jobTitle = new JobTitle(userMsg.getJobTitleName());
        }

        Company company = companyRepository.findByName(userMsg.getCompanyName());
        if(company == null){
            company = new Company(userMsg.getCompanyName());
        }



        companyRepository.save(company);
        jobTitleRepository.save(jobTitle);


        User user = new User(userMsg.getUsername(),
                            userMsg.getName(),
                            userMsg.getSurname());

        user.setCompany(company);
        user.setJobTitle(jobTitle);

        userRepository.save(user);


        UserPassword userPassword = new UserPassword(user.getId(),
                                        userMsg.getPassword());

        UserAddress userAddress = new UserAddress(user.getId(),
                                        userMsg.getStreet(),
                                        userMsg.getHouseNumber(),
                                        userMsg.getCity(),
                                        userMsg.getCountry());

        UserContact userContact = new UserContact(user.getId(),
                                        userMsg.getEmail(),
                                        userMsg.getPhoneNumber());

        userAddressRepository.save(userAddress);
        userContactRepository.save(userContact);
        userPasswordRepository.save(userPassword);



    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
