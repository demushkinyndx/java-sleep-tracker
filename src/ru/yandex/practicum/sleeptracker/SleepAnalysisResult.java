package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    private final String title;
    private final Object result;

    public  SleepAnalysisResult(String title, Object result) {
        this.title = title;
        this.result = result;
    }

    @Override
    public String toString() {
        return title + ": " + result;
    }
}
