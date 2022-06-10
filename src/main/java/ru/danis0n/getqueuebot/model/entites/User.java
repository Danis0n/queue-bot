package ru.danis0n.getqueuebot.model.entites;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @Column(name = "user_id")
    int id;
    @Column(name = "user_name")
    String name;
    @Column(name = "fk_bot_state_id")
    int state_id;

    @Override
    public String toString() {
        return "User " +
                "id = " + id +
                ", name = '" + name +
                ", state = " + state_id;
    }
}
