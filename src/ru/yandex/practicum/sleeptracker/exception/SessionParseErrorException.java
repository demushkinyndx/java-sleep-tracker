package ru.yandex.practicum.sleeptracker.exception;

public class SessionParseErrorException extends RuntimeException {
    public SessionParseErrorException(final String message) {
        super(message);
    }
}
