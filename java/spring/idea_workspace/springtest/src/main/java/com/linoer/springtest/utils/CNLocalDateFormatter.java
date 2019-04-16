package com.linoer.springtest.utils;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CNLocalDateFormatter implements Formatter<LocalDate> {
    public static final String CN_PATTERN = "yyyy/MM/dd";
    public static final String US_PATTERN = "dd/MM/yyyy";

    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern(getPattern(locale)));
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return DateTimeFormatter.ofPattern(getPattern(locale)).format(localDate);
    }

    public static String getPattern(Locale locale){
        return isCN(locale) ? CN_PATTERN : US_PATTERN;
    }

    public static boolean isCN(Locale locale){
        return Locale.CHINA.getCountry().equals(locale.getCountry());
    }
}
