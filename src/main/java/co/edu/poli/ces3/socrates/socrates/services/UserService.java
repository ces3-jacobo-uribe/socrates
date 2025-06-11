package co.edu.poli.ces3.socrates.socrates.services;

import co.edu.poli.ces3.socrates.socrates.dao.User;
import co.edu.poli.ces3.socrates.socrates.repositories.UserRepository;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        try{
            this.userRepository = new UserRepository();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try{
            users = this.userRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public User upgrade(User user) {
        try {
            return (User) userRepository.update(user);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
