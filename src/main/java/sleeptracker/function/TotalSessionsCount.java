package main.java.sleeptracker.function;

import main.java.sleeptracker.SleepAnalysisResult;
import main.java.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class TotalSessionsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int count = 0;
        if (sleepingSessions != null && !sleepingSessions.isEmpty()) {
            count = sleepingSessions.size();
        }

        return new SleepAnalysisResult("Количество сессий", count);
    }
}
