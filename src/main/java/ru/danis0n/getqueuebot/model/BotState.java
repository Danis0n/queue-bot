package ru.danis0n.getqueuebot.model;

public enum BotState {
    START(1),
    HELP(2),
    SHOWUSERS(3),
    ABOUT(4),
    SHOWLESSONS(5),
    LESSON(6),
    QUEUE(7),
    TOBOOK(8),
    BACK(9);

    private final int id;

    BotState(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name();
    }
    
}
