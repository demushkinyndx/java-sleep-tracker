package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    private final String title;
    private final Number result;

    public <T extends Number> SleepAnalysisResult(String title, T result) {
        this.title = title;
        this.result = result;
    }

    @Override
    public String toString() {
        return title + ": " + result;
    }
}
