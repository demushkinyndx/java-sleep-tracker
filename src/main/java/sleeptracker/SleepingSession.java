package main.java.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class SleepingSession {
    private final LocalDateTime sleepStart;
    private final LocalDateTime sleepEnd;
    private final SleepingQuality quality;

    public SleepingSession(LocalDateTime sleepStart, LocalDateTime sleepEnd, SleepingQuality quality) {
        this.sleepStart = sleepStart;
        this.sleepEnd = sleepEnd;
        this.quality = quality;
    }

    public SleepingQuality getQuality() {
        return quality;
    }

    public long getDurationMinutes() {
        return Duration.between(sleepStart, sleepEnd).toMinutes();
    }

    public LocalDateTime getSleepStart() {
        return sleepStart;
    }

    public LocalDateTime getSleepEnd() {
        return sleepEnd;
    }

    public boolean isSleepingNight() {
        LocalDate startDate = sleepStart.toLocalDate();
        LocalDate endDate = sleepEnd.toLocalDate();
        LocalTime startTime = sleepStart.toLocalTime();
        LocalTime endTime = sleepEnd.toLocalTime();

        if (!startDate.equals(endDate)) {
            //Если пользователь лёг спать в один день, а проснулся на следующий, он точно спал этой ночью.
            return true;
        }
        LocalTime nightEnd = LocalTime.of(6, 0);
        if (startTime.isBefore(nightEnd)) {
            return true;
        }

        return endTime.isBefore(nightEnd);
    }

    public Optional<ChronoType> getChronoType() {
        LocalTime startTime = sleepStart.toLocalTime();
        LocalTime endTime = sleepEnd.toLocalTime();

        //сова - после 23:00 -> после 9:00
        if (startTime.isAfter(LocalTime.of(23, 0)) && endTime.isAfter(LocalTime.of(9, 0))) {
            return Optional.of(ChronoType.OWL);
        }

        //жаворонок - до 22:00 -> до 07:00
        if (startTime.isBefore(LocalTime.of(22, 0)) && endTime.isBefore(LocalTime.of(7, 0))) {
            return Optional.of(ChronoType.LARK);
        }

        if (!isSleepingNight()) {
            return Optional.empty();
        }

        return Optional.of(ChronoType.DOVE);
    }
}
