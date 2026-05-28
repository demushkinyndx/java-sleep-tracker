package ru.yandex.practicum.sleeptracker;

public enum SleepingType {
    OWL("Сова"),           //  > 23:00,  > 9:00
    LARK("Жаворонок"),     //  < 22:00, < 7:00
    DOVE("Голубь");

    private final String name;

    SleepingType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}