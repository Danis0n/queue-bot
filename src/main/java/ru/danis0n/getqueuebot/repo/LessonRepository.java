package ru.danis0n.getqueuebot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danis0n.getqueuebot.model.entites.Lesson;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
    Lesson findById(long id);
    Lesson getById(long id);
    Lesson getByName(String name);
}
