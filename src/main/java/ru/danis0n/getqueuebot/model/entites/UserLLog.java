package ru.danis0n.getqueuebot.model.entites;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_llogs")
public class UserLLog {

    @Id
    @Column(name = "user_id")
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    Lesson lesson;

    @Column(name = "callbackdata")
    String data;
}
