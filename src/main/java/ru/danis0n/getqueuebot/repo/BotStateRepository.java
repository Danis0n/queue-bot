package ru.danis0n.getqueuebot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danis0n.getqueuebot.model.entites.State;

public interface BotStateRepository extends JpaRepository<State,Long> {
    State findById(long id);
    State getById(long id);
    void deleteById(long id);
}
