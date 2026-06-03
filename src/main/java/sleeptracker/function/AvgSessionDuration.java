package main.java.sleeptracker.function;

import main.java.sleeptracker.SleepAnalysisResult;
import main.java.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class AvgSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        double count = 0;
        if (sleepingSessions != null && !sleepingSessions.isEmpty()) {
            count = sleepingSessions.stream().mapToDouble(SleepingSession::getDurationMinutes)
                    .average()
                    .orElse(0);
        }

        return new SleepAnalysisResult("Средняя продолжительность сессии, минут", Math.round(count));
    }
}
