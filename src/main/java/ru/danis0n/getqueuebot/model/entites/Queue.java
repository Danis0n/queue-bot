package ru.danis0n.getqueuebot.model.entites;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Constraint;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lesson_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Queue {

    @Id
    @Column(name = "lesson_id")
    int lesson_id;
    @Column(name = "user_id")
    int user_id;

    @Override
    public String toString() {
        return "Queue " +
                " lesson_id=" + lesson_id +
                ", user_id=" + user_id;
    }
}
