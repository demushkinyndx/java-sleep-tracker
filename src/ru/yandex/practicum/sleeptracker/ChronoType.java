package ru.yandex.practicum.sleeptracker;

public enum ChronoType {
    LARK("Жаворонок"),     //  < 22:00, < 7:00
    OWL("Сова"),           //  > 23:00,  > 9:00
    DOVE("Голубь");

    private final String name;

    ChronoType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}