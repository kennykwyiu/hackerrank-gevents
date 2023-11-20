package com.hackerrank.gevents.utils;

import com.hackerrank.gevents.exception.InvalidEventTypeException;

public class ServiceUtils {
    public static void validateEventType(String eventType) throws InvalidEventTypeException {
        if (!(eventType.equals("PushEvent") ||
                eventType.equals("ReleaseEvent") ||
                eventType.equals("WatchEvent"))) {

            throw new InvalidEventTypeException("Given type is invalid");
        }
    }
}
