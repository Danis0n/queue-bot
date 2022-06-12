package ru.danis0n.getqueuebot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danis0n.getqueuebot.model.entites.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
    User getById(long id);
    boolean existsById(long id);
    void deleteById(long id);
}
