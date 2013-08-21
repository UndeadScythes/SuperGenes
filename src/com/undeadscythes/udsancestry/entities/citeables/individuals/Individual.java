package com.undeadscythes.udsancestry.entities.citeables.individuals;

import java.util.*;
import com.undeadscythes.udsancestry.entities.citeables.events.*;
import com.undeadscythes.udsancestry.exceptions.*;
import com.undeadscythes.udsancestry.record.*;
import com.undeadscythes.udsancestry.entities.citeables.*;
import com.undeadscythes.udsancestry.entities.citeables.properties.*;

/**
 * @author UndeadScythes
 */
public class Individual extends Citeable {
    private EnumMap<EventType, Event> events = new EnumMap<EventType, Event>(EventType.class);

    public Individual(RecordSchedule rs) {
        addProperty(IndividualProperty.ID, rs.getHeader().replace("@ INDI", "").replace("@P", "").trim());
        while(rs.hasNext()) {
            Record record = rs.getRecord();
            try {
                EventType type = EventType.getByTag(record.getHead().split(" ")[0]);
                events.put(type, new Event(new RecordSchedule(rs.nextLevel(), record)));
            } catch(NoPropertyExistsException ex) {
                try {
                    IndividualProperty type = IndividualProperty.getByTag(record.getHead().split(" ")[0]);
                    switch(type) {
                        case NAME:
                            addProperty(new Name(new RecordSchedule(rs.nextLevel(), record)));
                            break;
                        case EMBED:
                            addProperty(new Embed(new RecordSchedule(rs.nextLevel(), record)));
                            break;
                        case CITATION:
                            addCitation(rs.getLevel(), new RecordSchedule(rs.getLevel(), record).getRecord());
                            break;
                        case FB:
                            Property fb = new Property(type, record.getHead().replace(type.getTag(), "").trim());
                            fb.addCitation(rs.nextLevel(), new RecordSchedule(rs.nextLevel(), record).getRecord());
                            addProperty(fb);
                            break;
                        case SPOUSE_IN_FAMILY:
                        case CHILD_IN_FAMILY:
                            addProperty(type, record.getHead().replace(type.getTag(), "").replace("F", "").replace("@", "").trim());
                            break;
                        default:
                            addProperty(type, record.getHead().replace(type.getTag(), "").trim());
                    }
                } catch(NoPropertyExistsException ex2) {
                    System.out.println("INDIVIDUAL - " + record.getHead() + " (" + record.size() + ")"); //TODO: Remove debug message
                }
            }
        }
    }

    @Override
    public void debug(String prefix) {
        System.out.println("- NEW INDIVIDUAL:");
        super.debug("   ");
        if(!events.isEmpty()) {
            System.out.println(prefix + "   - EVENTS:");
            for(Map.Entry<EventType, Event> event : events.entrySet()) {
                System.out.println(prefix + "      - " + event.getKey().name() + ": ");
                event.getValue().debug(prefix + "         ");
            }
        }
    }

    public String getID() {
        try {
            return getPropertyValue(IndividualProperty.ID);
        } catch (PropertyNotSetException ex) {
            return "";
        }
    }

    public String getFullName() {
        String gender = "U";
        try {
            gender = getPropertyValue(IndividualProperty.GENDER);
        } catch (PropertyNotSetException ex) {}
        try {
            return ((Name)getProperty(IndividualProperty.NAME)).getFullName();
        } catch (PropertyNotSetException ex) {
            return (gender.equals("M") ? "Male" : (gender.equals("F") ? "Female" : "Unknown"));
        }
    }
}
