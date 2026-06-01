package ru.yandex.practicum.sleeptracker;

public enum ChronoType {
    LARK("Жаворонок"),     //  «Жаворонок» — если время засыпания было до 22:00, а время пробуждения до — 7:00.
    OWL("Сова"),           // «Сова» — если время засыпания было после 23:00, а время пробуждения — после 9:00.
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