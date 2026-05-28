package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class SleeplessNightsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long count = 0;
        if (sleepingSessions != null && !sleepingSessions.isEmpty()) {
            count = sleepingSessions.stream().filter(
                    SleepingSession::isSleeplessNight
            ).count();

        }

        return new SleepAnalysisResult("Количество бессонных ночей", count);
    }
}
