package main.java.sleeptracker;

import main.java.sleeptracker.exception.SessionParseErrorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataLoader {
    private static final DateTimeFormatter SESSION_DT_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public List<SleepingSession> loadSleepData(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(this::parseSleepSession)
                    .collect(Collectors.toList());
        }
    }


    private SleepingSession parseSleepSession(String line) throws SessionParseErrorException {
        var peaces = line.split(";");
        if (peaces.length != 3) throw new SessionParseErrorException("Не удалось разобрать запись трека: " + line);

        return new SleepingSession(
                LocalDateTime.parse(peaces[0], SESSION_DT_FORMATTER),
                LocalDateTime.parse(peaces[1], SESSION_DT_FORMATTER),
                SleepingQuality.valueOf(peaces[2])
        );
    }
}
