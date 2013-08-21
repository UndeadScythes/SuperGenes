package com.undeadscythes.udsancestry.entities.citeables.properties;

import com.undeadscythes.udsancestry.entities.citeables.properties.NamedProperty;
import com.undeadscythes.udsancestry.entities.citeables.properties.Property;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;

/**
 * @author UndeadScythes
 */
public class Embed extends Property {
    private String file;
    private String form;
    private String title;

    public Embed(RecordSchedule rs) {
        super(new NamedProperty("EMBED"), rs.getHeader().replace("OBJE", "").trim());
        while(rs.hasNext()) {
            Record record = rs.getRecord();
            if(record.headBegins("FILE")) {
                file = record.getHead();
            } else if(record.headBegins("FORM")) {
                form = record.getHead();
            } else if(record.headBegins("TITL")) {
                title = record.getHead();
            }
        }
    }

    @Override
    public void debug(String prefix) {
        System.out.println(prefix + "- OBJECT: " + form);
        System.out.println(prefix + "-    TITLE: " + title);
        System.out.println(prefix + "-    FILE: " + file);
        System.out.println(prefix + "- ACTUAL VALUE:");
        super.debug(prefix + "   ");
    }
}
