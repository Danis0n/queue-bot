package ru.danis0n.getqueuebot.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danis0n.getqueuebot.model.entites.User;
import ru.danis0n.getqueuebot.repo.UserRepository;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDAO {

    final UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserId(long id) {
        return userRepository.findById(id);
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public boolean isExistById(long id){
        return userRepository.existsById(id);
    }

    public User getById(long id){
        return userRepository.getById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean isExist(long id) {
        User user = findByUserId(id);
        return user != null;
    }

}
