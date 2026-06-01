package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long sleepingNightsCount = sleepingSessions.stream().filter(
                SleepingSession::isSleepingNight
        ).count();

        //здесь полагаем, что записи в логе идут в хронологическом порядке и не нужно искать максимальную и минимальную даты
        LocalDateTime firstDateTime = sleepingSessions.stream()
                .findFirst()
                .map(SleepingSession::getSleepStart)
                .orElseThrow(() -> new IllegalStateException("Не удалось найти первую дату в сессиях"));

        LocalDateTime lastDateTime = sleepingSessions.stream()
                .reduce((a, b) -> b) // оставляет последний элемент
                .map(SleepingSession::getSleepStart)
                .orElse(firstDateTime);

        long nightsTotal = ChronoUnit.DAYS.between(firstDateTime.toLocalDate(), lastDateTime.toLocalDate());

        return new SleepAnalysisResult("Количество бессонных ночей", nightsTotal - sleepingNightsCount);
    }
}
