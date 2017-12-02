package edu.karazin.shop.service.impl;

import edu.karazin.shop.dao.UsersDao;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user){
        if (usersDao.getUserByUsername(user.getUsername()) == null){
            user.setRole(user.getUsername().equals("admin") ? "ROLE_ADMIN" : "ROLE_USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usersDao.save(user);
            return true;
        } else return false;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) usersDao.findAll();
    }

    @Override
    public User getUserByName(String login){
        return usersDao.getUserByUsername(login);
    }

    @Override
    public User getUserById(Integer id) {
        return usersDao.findOne(id);
    }

    @Override
    public User updateUser(User user) {
        return usersDao.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        usersDao.delete(id);
    }
}
