package pl.bs.roomBooker.service.user;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.models.user.*;
import pl.bs.roomBooker.repository.user.*;

import java.util.List;

@Service
class UserDao {

    private final UserRepository userRepository;
    private final UserPasswordRepository userPasswordRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserContactRepository userContactRepository;
    private final JobTitleRepository jobTitleRepository;
    private final CompanyRepository companyRepository;

    UserDao(UserRepository userRepository,
            UserPasswordRepository userPasswordRepository,
            UserAddressRepository userAddressRepository,
            UserContactRepository userContactRepository,
            JobTitleRepository jobTitleRepository,
            CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.userPasswordRepository = userPasswordRepository;
        this.userAddressRepository = userAddressRepository;
        this.userContactRepository = userContactRepository;
        this.jobTitleRepository = jobTitleRepository;
        this.companyRepository = companyRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User %s does not exist", id)));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveJobTitle(JobTitle jobTitle) {
        jobTitleRepository.save(jobTitle);
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public void saveUserPassword(UserPassword userPassword) {
        userPasswordRepository.save(userPassword);
    }

    public void saveUserAddress(UserAddress userAddress) {
        userAddressRepository.save(userAddress);
    }

    public void saveUserContact(UserContact userContact) {
        userContactRepository.save(userContact);
    }

    public JobTitle findJobByName(String jobTitleName) {
        return jobTitleRepository.findByJobName(jobTitleName);
    }

    public Company findCompanyByName(String companyName) {
        return companyRepository.findByName(companyName);
    }

}
