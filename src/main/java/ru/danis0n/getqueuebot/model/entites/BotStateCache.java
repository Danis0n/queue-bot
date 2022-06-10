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
public class BotStateCache {
    @Id
    @Column(name = "bot_state_id")
    int id;
    @Column(name = "state_name")
    String state_name;
}
