package ru.danis0n.getqueuebot.model.entites;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {

    @Id
    @Column(name = "lesson_id")
    int id;
    @Column(name = "lesson_cab")
    String cab_num;
    @Column(name = "lesson_name")
    String name;
    @Column(name = "teacher_name")
    String teacher_name;

    @Override
    public String toString() {
        return "Lesson " +
                "id = " + id +
                ", cab_num = '" + cab_num +
                ", name= '" + name +
                ", teacher_name= '" + teacher_name;
    }
}
