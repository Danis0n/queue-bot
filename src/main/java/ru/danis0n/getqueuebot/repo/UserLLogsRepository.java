package ru.danis0n.getqueuebot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danis0n.getqueuebot.model.entites.UserLLog;

public interface UserLLogsRepository extends JpaRepository<UserLLog,Long> {
    UserLLog findById(long id);
    UserLLog getById(long id);
}
