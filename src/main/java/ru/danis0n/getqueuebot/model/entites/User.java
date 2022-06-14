package ru.danis0n.getqueuebot.model.entites;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    long id;

    @Column(name = "user_name")
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_state")
    State state;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", botState=" + state +
                '}';
    }
}