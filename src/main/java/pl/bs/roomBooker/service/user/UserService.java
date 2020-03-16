package pl.bs.roomBooker.service.user;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.UserMsg;
import pl.bs.roomBooker.models.user.*;

import java.util.List;

@Service
public class UserService {

    private final UserCreator userCreator;
    private final UserDao userDao;

    public UserService(UserCreator userCreator, UserDao userDao) {
        this.userCreator = userCreator;
        this.userDao = userDao;
    }

    public User findById(Long id) {
        return userDao.findUserById(id);
    }

    public void create(UserMsg userMsg) {
        userCreator.create(userMsg);
    }

    public void delete(Long id) {
        userDao.deleteUserById(id);
    }

    public List<User> getAll() {
        return userDao.findAllUsers();
    }

    public List<Company> getAllCompanies() {
        return userDao.findAllCompanies();
    }
}
