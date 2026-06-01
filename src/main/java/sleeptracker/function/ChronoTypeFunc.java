package main.java.sleeptracker.function;

import main.java.sleeptracker.ChronoType;
import main.java.sleeptracker.SleepAnalysisResult;
import main.java.sleeptracker.SleepingSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChronoTypeFunc implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        Map<ChronoType, Long> counts = sleepingSessions.stream()
                .map(SleepingSession::getChronoType)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long max = counts.values().stream().mapToLong(Long::longValue).max().orElse(0);

        Optional<ChronoType> maxKey = counts.entrySet().stream()
                .filter(entry -> entry.getValue() == max)
                .map(Map.Entry::getKey)
                .findFirst();

        return maxKey.map(key -> new SleepAnalysisResult("Хронотип", key))
                .orElse(new SleepAnalysisResult("Хронотип", null));
    }
}
