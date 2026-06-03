package main.java.sleeptracker.function;

import main.java.sleeptracker.SleepAnalysisResult;
import main.java.sleeptracker.SleepingQuality;
import main.java.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class BadQualitySessionsCount  implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long count = sleepingSessions.stream()
                .filter(session -> session.getQuality() == SleepingQuality.BAD)
                .count();

        return new SleepAnalysisResult("Количество сессий с плохим качеством сна", count);
    }
}
