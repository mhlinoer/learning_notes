package com.linoer.app.test.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class USLocalDateFormatter implements Formatter<LocalDate> {
    public static final String US_PATTERN = "MM/dd/yyyy";
    public static final String NORMAL_PATTERN = "dd/MM/yyyy";

    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern(getPattern(locale)));
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return DateTimeFormatter.ofPattern(getPattern(locale)).format(localDate);
    }
    private static String getPattern(Locale locale){
        return isUniteStates(locale)?US_PATTERN:NORMAL_PATTERN;
    }
    private static boolean isUniteStates(Locale locale){
        return Locale.US.getCountry().equals(locale.getCountry());
    }
}
