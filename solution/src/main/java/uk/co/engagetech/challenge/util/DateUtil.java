package uk.co.engagetech.challenge.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat CLIENT_DATE_FMT = new SimpleDateFormat("dd/MM/yyyy");

    public static Date parseClientDate(String value) {
        if (value == null) {
            return null;
        } else {
            try {
                return CLIENT_DATE_FMT.parse(value);
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public static String toClientDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return CLIENT_DATE_FMT.format(date);
        }
    }
}
