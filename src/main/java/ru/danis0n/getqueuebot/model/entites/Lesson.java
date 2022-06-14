package ru.danis0n.getqueuebot.model.entites;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "lesson_id")
    long id;
    @Column(name = "lesson_name")
    String name;
    @Column(name = "lesson_strid")
    String stdId;

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stdId='" + stdId + '\'' +
                '}';
    }
}
