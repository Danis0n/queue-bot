package ru.danis0n.getqueuebot.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danis0n.getqueuebot.model.entites.UserLLog;
import ru.danis0n.getqueuebot.repo.UserLLogsRepository;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLLogDAO {

    final UserLLogsRepository userLLogsRepository;

    @Autowired
    public UserLLogDAO(UserLLogsRepository userLLogsRepository){
        this.userLLogsRepository = userLLogsRepository;
    }

    public UserLLog getById(long id){
        return userLLogsRepository.getById(id);
    }

    public UserLLog findById(long id){
        return userLLogsRepository.findById(id);
    }

    public void save(UserLLog userLLog){
        userLLogsRepository.save(userLLog);
    }

    public List<UserLLog> findAll(){
        return userLLogsRepository.findAll();
    }

}
