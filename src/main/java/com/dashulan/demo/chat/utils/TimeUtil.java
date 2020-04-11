package com.dashulan.demo.chat.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime stringToLocalDateTime(String time) {
        return LocalDateTime.parse(time,pattern);
    }

    public static String localDateTimeToString(LocalDateTime time) {
        return time.format(pattern);
    }
}
