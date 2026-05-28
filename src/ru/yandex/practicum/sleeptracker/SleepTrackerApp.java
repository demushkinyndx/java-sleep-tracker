package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exception.SessionParseErrorException;
import ru.yandex.practicum.sleeptracker.function.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class SleepTrackerApp {

    static final String SLEEP_LOG = "src/resources/sleep_log.txt";

    public static void main(String[] args) {
        List<Function<List<SleepingSession>, SleepAnalysisResult>> functionList = new ArrayList<>();
        functionList.add(new TotalSessionsCount());
        functionList.add(new BadQualitySessionsCount());
        functionList.add(new MinSessionDuration());
        functionList.add(new MaxSessionDuration());
        functionList.add(new AvgSessionDuration());
        functionList.add(new SleeplessNightsCount());

        try {
            List<SleepingSession> sleepSessions = new DataLoader().loadSleepData(SLEEP_LOG);
            for (SleepingSession session : sleepSessions) {
                System.out.println(String.format("start: %s, end: %s, бессонная: %s, хронотип: %s", session.getSleepStart(), session.getSleepEnd(), session.isSleeplessNight(), session.getChronoType()));
            }
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