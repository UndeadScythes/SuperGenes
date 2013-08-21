package com.undeadscythes.udsancestry.record;

import java.util.*;

/**
 * @author UndeadScythes
 */
public class RecordSchedule {
    private String line;
    private String header;
    private String level;
    private Iterator<String> i;
    private boolean EOF = false;

    public RecordSchedule(String level, Record record) {
        this.level = level;
        this.i = record.iterator();
        this.header = i.next();
        if(i.hasNext()) {
            line = i.next();
        }
        if(!i.hasNext()) {
            EOF = true;
        }
    }

    public Record getRecord() {
        Record record = new Record();
        record.add(line.replaceFirst(level, ""));
        if(!i.hasNext()) {
            EOF = true;
        }
        while(i.hasNext()) {
            line = i.next();
            if(line.startsWith(level)) break;
            record.add(line);
            if(!i.hasNext()) {
                EOF = true;
            }
        }
        return record;
    }

    public String getLevel() {
        return level;
    }

    public String getHeader() {
        return header;
    }

    public boolean headerBegins(String header) {
        return header.startsWith(header);
    }

    public boolean hasNext() {
        return !EOF;
    }

    public String nextLevel() {
        return Integer.toString(Integer.parseInt(level.trim()) + 1) + " ";
    }
}
