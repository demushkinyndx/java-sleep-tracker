package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exception.SessionParseErrorException;
import ru.yandex.practicum.sleeptracker.function.AvgSessionDuration;
import ru.yandex.practicum.sleeptracker.function.BadQualitySessionsCount;
import ru.yandex.practicum.sleeptracker.function.MaxSessionDuration;
import ru.yandex.practicum.sleeptracker.function.MinSessionDuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class SleepTrackerApp {

    static final String SLEEP_LOG = "src/resources/sleep_log.txt";
    private static List<Function<List<SleepingSession>, SleepAnalysisResult>> functionList;

    public static void main(String[] args) {
        functionList = new ArrayList<>();
        functionList.add(new BadQualitySessionsCount());
        functionList.add(new MinSessionDuration());
        functionList.add(new MaxSessionDuration());
        functionList.add(new AvgSessionDuration());

        try {
            List<SleepingSession> sleepSessions = new DataLoader().loadSleepData(SLEEP_LOG);
            functionList.stream()
                    .map(function -> function.apply(sleepSessions))
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SessionParseErrorException e) {
            System.out.println(e.getMessage());
        }
    }

}