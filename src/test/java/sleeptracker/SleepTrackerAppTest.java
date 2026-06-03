package test.java.sleeptracker;

import main.java.sleeptracker.ChronoType;
import main.java.sleeptracker.SleepAnalysisResult;
import main.java.sleeptracker.SleepingQuality;
import main.java.sleeptracker.SleepingSession;
import main.java.sleeptracker.function.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SleepTrackerAppTest {

    private List<SleepingSession> createSampleSessions() {
        return Arrays.asList(
                new SleepingSession(LocalDateTime.of(2023, 10, 1, 23, 15),
                        LocalDateTime.of(2023, 10, 2, 7, 30), SleepingQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2023, 10, 2, 23, 50),
                        LocalDateTime.of(2023, 10, 3, 6, 40), SleepingQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2023, 10, 3, 14, 10),
                        LocalDateTime.of(2023, 10, 3, 15, 0), SleepingQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2023, 10, 3, 23, 40),
                        LocalDateTime.of(2023, 10, 4, 8, 0), SleepingQuality.BAD),
                new SleepingSession(LocalDateTime.of(2023, 10, 5, 0, 10),
                        LocalDateTime.of(2023, 10, 5, 6, 20), SleepingQuality.GOOD)
        );
    }

    @Test
    void testAvgSessionDuration() {
        List<SleepingSession> sessions = createSampleSessions();

        AvgSessionDuration avgFunc = new AvgSessionDuration();
        SleepAnalysisResult result = avgFunc.apply(sessions);

        double totalMinutes = sessions.stream().mapToLong(SleepingSession::getDurationMinutes).sum();
        long count = sessions.size();
        long expectedAverage = Math.round(totalMinutes / (double) count);

        assertEquals("Средняя продолжительность сессии, минут", result.getTitle());
        assertEquals(expectedAverage, result.getResult());
    }

    @Test
    void testMaxSessionDuration() {
        List<SleepingSession> sessions = createSampleSessions();

        MaxSessionDuration maxFunc = new MaxSessionDuration();
        SleepAnalysisResult result = maxFunc.apply(sessions);

        long expectedMax = sessions.stream().mapToLong(SleepingSession::getDurationMinutes).max().orElse(0);

        assertEquals("Максимальная продолжительность сессии, минут", result.getTitle());
        assertEquals(expectedMax, result.getResult());
    }

    @Test
    void testMinSessionDuration() {
        List<SleepingSession> sessions = createSampleSessions();

        MinSessionDuration minFunc = new MinSessionDuration();
        SleepAnalysisResult result = minFunc.apply(sessions);

        long expectedMin = sessions.stream().mapToLong(SleepingSession::getDurationMinutes).min().orElse(0);

        assertEquals("Минимальная продолжительность сессии, минут", result.getTitle());
        assertEquals(expectedMin, result.getResult());
    }


    @Test
    void testBadQualitySessionsCount() {
        List<SleepingSession> sessions = createSampleSessions();

        BadQualitySessionsCount badCountFunc = new BadQualitySessionsCount();
        SleepAnalysisResult result = badCountFunc.apply(sessions);

        long expectedCount = sessions.stream()
                .filter(s -> s.getQuality() == SleepingQuality.BAD)
                .count();

        assertEquals("Количество сессий с плохим качеством сна", result.getTitle());
        assertEquals(expectedCount, result.getResult());
    }

    @Test
    void testChronoTypeDoveFunction() {
        SleepingSession session1 = new SleepingSession(LocalDateTime.of(2023, 10, 1, 22, 0),
                LocalDateTime.of(2023, 10, 2, 6, 0), SleepingQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.of(2023, 10, 2, 23, 0),
                LocalDateTime.of(2023, 10, 3, 7, 0), SleepingQuality.GOOD);
        SleepingSession session3 = new SleepingSession(LocalDateTime.of(2023, 10, 3, 22, 30),
                LocalDateTime.of(2023, 10, 4, 6, 30), SleepingQuality.GOOD);

        List<SleepingSession> sessions = new ArrayList<>(List.of(session1, session2, session3));

        ChronoTypeFunc func = new ChronoTypeFunc();

        SleepAnalysisResult result = func.apply(sessions);

        assertEquals("Хронотип", result.getTitle());
        assertEquals(ChronoType.DOVE, result.getResult());

    }


    @Test
    void testChronoTypeLarkFunction() {
        List<SleepingSession> sessions = new ArrayList<>(List.of(
                new SleepingSession(LocalDateTime.of(2023, 10, 1, 22, 0),
                        LocalDateTime.of(2023, 10, 2, 6, 0), SleepingQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2023, 10, 2, 23, 0),
                        LocalDateTime.of(2023, 10, 3, 7, 0), SleepingQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2023, 10, 3, 22, 30),
                        LocalDateTime.of(2023, 10, 4, 6, 30), SleepingQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2023, 10, 4, 21, 0),
                        LocalDateTime.of(2023, 10, 5, 6, 0), SleepingQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2023, 10, 6, 21, 0),
                        LocalDateTime.of(2023, 10, 7, 6, 59), SleepingQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2023, 10, 7, 21, 0),
                        LocalDateTime.of(2023, 10, 8, 5, 30), SleepingQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2023, 10, 8, 21, 30),
                        LocalDateTime.of(2023, 10, 9, 3, 30), SleepingQuality.GOOD)
        ));
        ChronoTypeFunc func = new ChronoTypeFunc();

        SleepAnalysisResult result = func.apply(sessions);
        assertEquals(ChronoType.LARK, result.getResult());
    }

    @Test
    void testChronoTypeOwlFunction() {
        List<SleepingSession> sessions = new ArrayList<>(List.of(
                new SleepingSession(LocalDateTime.of(2023, 10, 1, 22, 0),
                        LocalDateTime.of(2023, 10, 2, 6, 0), SleepingQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2023, 10, 3, 21, 0),
                        LocalDateTime.of(2023, 10, 4, 5, 30), SleepingQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2023, 10, 4, 23, 1),
                        LocalDateTime.of(2023, 10, 5, 9, 30), SleepingQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2023, 10, 5, 1, 59),
                        LocalDateTime.of(2023, 10, 5, 10, 30), SleepingQuality.GOOD)
        ));
        ChronoTypeFunc func = new ChronoTypeFunc();
        SleepAnalysisResult result = func.apply(sessions);
        assertEquals(ChronoType.OWL, result.getResult());
    }

    @Test
    void testSessionChronoType() {
        HashMap<SleepingSession, ChronoType> sessions = new HashMap<>();
        sessions.put(new SleepingSession(LocalDateTime.of(2023, 10, 1, 23, 1),
                LocalDateTime.of(2023, 10, 2, 9, 1), null), ChronoType.OWL);

        sessions.put(new SleepingSession(LocalDateTime.of(2023, 10, 1, 21, 30),
                LocalDateTime.of(2023, 10, 2, 6, 59), null), ChronoType.LARK);

        sessions.put(new SleepingSession(LocalDateTime.of(2023, 10, 1, 5, 30),
                LocalDateTime.of(2023, 10, 1, 8, 59), null), ChronoType.DOVE);
        for (Map.Entry entry : sessions.entrySet()) {
            SleepingSession session = (SleepingSession) entry.getKey();
            ChronoType chronoType = (ChronoType) entry.getValue();
            assertEquals(chronoType, session.getChronoType().get(),
                    "Ошибка определения хронотипа: start: " + session.getSleepStart() + ", end: " + session.getSleepEnd());
        }
    }

    @Test
    void testIsSleepingNight() {
        HashMap<SleepingSession, Boolean> sessions = new HashMap<>();
        sessions.put(new SleepingSession(LocalDateTime.of(2023, 10, 1, 22, 0),
                LocalDateTime.of(2023, 10, 2, 6, 0), null), true);

        sessions.put(new SleepingSession(LocalDateTime.of(2023, 10, 1, 21, 30),
                LocalDateTime.of(2023, 10, 1, 23, 59), null), false);

        sessions.put(new SleepingSession(LocalDateTime.of(2023, 10, 1, 5, 30),
                LocalDateTime.of(2023, 10, 1, 8, 59), null), true);

        sessions.put(new SleepingSession(LocalDateTime.of(2023, 10, 1, 0, 0),
                LocalDateTime.of(2023, 10, 1, 3, 0), null), true);

        sessions.put(new SleepingSession(LocalDateTime.of(2023, 10, 1, 23, 0),
                LocalDateTime.of(2023, 10, 2, 0, 0), null), true);

        for (Map.Entry entry : sessions.entrySet()) {
            SleepingSession session = (SleepingSession) entry.getKey();
            Boolean expectedIsNight = (Boolean) entry.getValue();
            assertEquals(expectedIsNight, session.isSleepingNight(),
                    "Ошибка определения ночи со сном: start: " + session.getSleepStart() + ", end: " + session.getSleepEnd());
        }
    }

    @Test
    void testSleeplessNightsCount() {
        List<SleepingSession> sessions = new ArrayList<>(List.of(
                new SleepingSession(LocalDateTime.of(2023, 10, 1, 22, 0),
                        LocalDateTime.of(2023, 10, 1, 22, 30), SleepingQuality.GOOD)
        ));
        SleeplessNightsCount sleeplessCount = new SleeplessNightsCount();
        SleepAnalysisResult result = sleeplessCount.apply(sessions);
        assertEquals(1L, result.getResult(), "Ошибка подсчета одной бессонной ночи");

        sessions.add(new SleepingSession(LocalDateTime.of(2023, 10, 2, 22, 0),
                LocalDateTime.of(2023, 10, 3, 6, 0), SleepingQuality.GOOD));

        result = sleeplessCount.apply(sessions);
        assertEquals(1L, result.getResult(), "Ошибка подсчета одной бессонной ночи в двух записях");

        sessions.add(new SleepingSession(LocalDateTime.of(2023, 10, 3, 23, 0),
                LocalDateTime.of(2023, 10, 4, 6, 0), SleepingQuality.GOOD));
        sessions.add(new SleepingSession(LocalDateTime.of(2023, 10, 4, 23, 0),
                LocalDateTime.of(2023, 10, 5, 7, 0), SleepingQuality.GOOD));
        sessions.add(new SleepingSession(LocalDateTime.of(2023, 10, 6, 23, 0),
                LocalDateTime.of(2023, 10, 7, 7, 0), SleepingQuality.GOOD));

        result = sleeplessCount.apply(sessions);

        long expectedNights = 2;

        assertEquals("Количество бессонных ночей", result.getTitle());
        assertEquals(expectedNights, result.getResult(), "Ошибка подсчета бессонных ночей");
    }

    @Test
    void testTotalSessionsCount() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(LocalDateTime.of(2023, 10, 1, 22, 0),
                        LocalDateTime.of(2023, 10, 2, 6, 0), SleepingQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2023, 10, 3, 23, 0),
                        LocalDateTime.of(2023, 10, 4, 6, 0), SleepingQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2023, 10, 4, 23, 0),
                        LocalDateTime.of(2023, 10, 5, 7, 0), SleepingQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2023, 10, 6, 23, 0),
                        LocalDateTime.of(2023, 10, 7, 7, 0), SleepingQuality.GOOD)
        );
        TotalSessionsCount totalSessionsCount = new TotalSessionsCount();
        SleepAnalysisResult result = totalSessionsCount.apply(sessions);
        assertEquals(4, result.getResult());
    }
}