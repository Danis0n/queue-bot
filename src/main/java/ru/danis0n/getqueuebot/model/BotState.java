package ru.danis0n.getqueuebot.model;

public enum BotState {
    START(1),
    HELP(2),
    SHOWUSERS(3);

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
