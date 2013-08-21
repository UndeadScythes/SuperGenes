package com.undeadscythes.udsancestry.entities.citeables.events;

import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;
import com.undeadscythes.udsancestry.entities.citeables.Citeable;

/**
 * @author UndeadScythes
 */
public class Event extends Citeable {
    public Event(RecordSchedule rs) {
        while(rs.hasNext()) {
            Record record = rs.getRecord();
            try {
                EventProperty type = EventProperty.getByTag(record.getHead().split(" ")[0]);
                switch(type) {
                    case DATE:
                        String date = record.getHead().replace(type.getTag(), "").trim();
                        date = date.replace("Jan/Feb/Mar", "Q1");
                        date = date.replace("Apr/May/Jun", "Q2");
                        date = date.replace("Jul/Aug/Sep", "Q3");
                        date = date.replace("Oct/Nov/Dec", "Q4");
                        addProperty(type, date);
                        break;
                    case PLACE:
                        addProperty(type, record.getHead().replace(type.getTag(), "").trim());
                        break;
                    case SOUR:
                        addCitation(rs.nextLevel(), record);
                        break;
                }
            } catch (NoPropertyExistsException ex) {
                System.out.println("EVENT - " + record.getHead() + " (" + record.size() + ")"); //TODO: Remove debug message
            }
        }
    }
}

