package com.undeadscythes.supergenes.event;

import static java.lang.Integer.*;
import org.apache.commons.lang3.*;
import static org.apache.commons.lang3.ArrayUtils.*;

/**
 * @author UndeadScythes
 */
public class Date {
    private static String MONTHS = "Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec";
    private static String[] MONTHS_LONG = {
        "january",
        "february",
        "march",
        "april",
        "may",
        "june",
        "july",
        "august",
        "september",
        "october",
        "november",
        "december"
    };

    /**
     *
     */
    public final int year;
    /**
     *
     */
    public final int quarter;
    /**
     *
     */
    public final int month;
    /**
     *
     */
    public final int day;
    /**
     *
     */
    public final Accuracy accuracy;

    /**
     *
     * @param year
     * @param month
     * @param day
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.quarter = (month - 1) / 3 + 1;
        this.accuracy = Accuracy.EXACT;
    }

    /**
     *
     * @param date
     */
    public Date(String date) {
        int y = 0;
        int q = 0;
        int m = 0;
        int d = 0;
        Accuracy a = Accuracy.EXACT;
        for (String split : date.split(" ")) {
            if (split.startsWith("Q") && q == 0) {
                q = parseInt(split.replace("Q", ""));
                continue;
            }
            if (contains(MONTHS_LONG, split.toLowerCase()) && m == 0) {
                m = indexOf(MONTHS_LONG, split.toLowerCase());
                continue;
            }
            if (split.length() == 4 && y == 0) {
                try {
                    y = parseInt(split);
                    if (y < 32) y = 0;
                } catch (NumberFormatException ex) {}
            }
            if (split.length() == 3 && m == 0) {
                m = MONTHS.toLowerCase().indexOf(split.toLowerCase()) / 4 + 1;
                if (m < 0) m = 0;
            }
            if (split.length() < 3 && d == 0) {
                try {
                    d = parseInt(split);
                } catch (NumberFormatException ex) {}
            }
            if (split.toLowerCase().startsWith("abt")) {
                a = Accuracy.ABOUT;
            }
            if (split.toLowerCase().startsWith("bet")) {
                a = Accuracy.BETWEEN;
            }
            if (split.length() > 4 && a.equals(Accuracy.BETWEEN) && y == 0) {
                try {
                    y = (parseInt(split.split(" - ")[0]) + parseInt(split.split(" - ")[1])) / 2;
                } catch (NumberFormatException ex) {}
            }
            if (split.length() > 4 && q == 0 && q == 0) {
                q = MONTHS.toLowerCase().indexOf(split.split("/")[0].toLowerCase()) / 12 + 1;
            }
        }
        this.year = y;
        this.quarter = q;
        this.month = m;
        this.day = d;
        this.accuracy = a;
    }

    /**
     *
     * @return
     */
    public String getString() {
        String m = (month == 0 ? (quarter == 0 ? "???" : "Q:" + quarter) : MONTHS.substring((month - 1) * 4, month * 4 - 1));
        String d = String.valueOf(day);
        String y = String.valueOf(year);
        while (y.length() < 4) {
            y = "0" + y;
        }
        if (d.length() == 1) d = "0" + d;
        return y + (accuracy.equals(Accuracy.ABOUT) ? "~" : "-") + m + "-" + (d.equals("00") ? "??" : d);
    }
}
