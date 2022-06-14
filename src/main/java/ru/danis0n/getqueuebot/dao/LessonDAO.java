package ru.danis0n.getqueuebot.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danis0n.getqueuebot.model.entites.Lesson;
import ru.danis0n.getqueuebot.repo.LessonRepository;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonDAO {

    final LessonRepository lessonRepository;

    @Autowired
    public LessonDAO(LessonRepository lessonRepository){
        this.lessonRepository = lessonRepository;
    }

    public Lesson findById(long id){
        return lessonRepository.findById(id);
    }

    public Lesson getById(long id){
        return lessonRepository.getById(id);
    }

    public List<Lesson> findAll(){
        return lessonRepository.findAll();
    }

    public void save(Lesson lesson){
        lessonRepository.save(lesson);
    }

    public void delete(Lesson lesson){
        lessonRepository.delete(lesson);
    }

}
