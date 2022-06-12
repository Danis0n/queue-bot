package ru.danis0n.getqueuebot.model.entites;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Proxy;
import ru.danis0n.getqueuebot.model.BotState;

import javax.persistence.*;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "bot_states")
public class State {

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "state_id")
    long id;

    @Column(name = "state_name")
    String botState;

    public BotState getBotStateEnum(String botState){
        return BotState.valueOf(botState);
    }

    @Override
    public String toString() {
        return "BotState{" +
                "id=" + id +
                ", name='" + botState + '\'' +
                '}';
    }
}
