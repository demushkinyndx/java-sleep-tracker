package main.java.sleeptracker.function;

import main.java.sleeptracker.SleepAnalysisResult;
import main.java.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class MaxSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long count = 0;
        if (sleepingSessions != null && !sleepingSessions.isEmpty()) {
            count = sleepingSessions.stream().mapToLong(SleepingSession::getDurationMinutes)
                    .max()
                    .orElse(0L);
        }


        return new SleepAnalysisResult("Максимальная продолжительность сессии, минут", count);
    }
}
