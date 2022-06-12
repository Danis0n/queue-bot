package ru.danis0n.getqueuebot.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danis0n.getqueuebot.model.entites.State;
import ru.danis0n.getqueuebot.repo.BotStateRepository;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StateDAO {

    final BotStateRepository botStateRepository;

    @Autowired
    public StateDAO(BotStateRepository botStateRepository) {
        this.botStateRepository = botStateRepository;
    }

    public void deleteById(long id){
        botStateRepository.deleteById(id);
    }

    public State findByStateId(long id) {
        return botStateRepository.findById(id);
    }

    public void save(State state){
        botStateRepository.save(state);
    }

    public State getById(long id){
        return botStateRepository.getById(id);
    }

    public List<State> findAllStates() {
        return botStateRepository.findAll();
    }

}
