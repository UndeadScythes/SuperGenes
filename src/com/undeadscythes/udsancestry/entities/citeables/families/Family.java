package com.undeadscythes.udsancestry.entities.citeables.families;

import com.undeadscythes.udsancestry.entities.citeables.events.EventType;
import com.undeadscythes.udsancestry.entities.citeables.events.Event;
import java.util.*;
import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;
import com.undeadscythes.udsancestry.entities.citeables.properties.Property;
import com.undeadscythes.udsancestry.exceptions.PropertyNotSetException;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;
import com.undeadscythes.udsancestry.entities.citeables.Citeable;

/**
 * @author UndeadScythes
 */
public class Family extends Citeable {
    private ArrayList<Property> children = new ArrayList<Property>(0);
    private EnumMap<EventType, Event> events = new EnumMap<EventType, Event>(EventType.class);

    public Family(RecordSchedule rs) {
        addProperty(FamilyProperty.ID, rs.getHeader().replace("@F", "").replace("@ FAM", "").trim());
        while(rs.hasNext()) {
            Record record = rs.getRecord();
            try {
                EventType type = EventType.getByTag(record.getHead().split(" ")[0]);
                events.put(type, new Event(new RecordSchedule(rs.nextLevel(), record)));
            } catch(NoPropertyExistsException ex) {
                try {
                    FamilyProperty type = FamilyProperty.getByTag(record.getHead().split(" ")[0]);
                    switch(type) {
                        case CHILD:
                            Property child = new Property(FamilyProperty.ID, record.getHead().replace(type.getTag(), "").replace("@", "").replace("P", "").trim());
                            RecordSchedule rs2 = new RecordSchedule(rs.nextLevel(), record);
                            while(rs2.hasNext()) {
                                Record record2 = rs2.getRecord();
                                for(String tag : record2) {
                                    try {
                                        FamilyProperty type2 = FamilyProperty.getByTag(tag.split(" ")[0]);
                                        child.addProperty(new Property(type2, tag.split(" ")[1]));
                                    } catch (NoPropertyExistsException ex2) {
                                        System.out.println("CHILD - " + tag); //TODO: Remove debug message
                                    }
                                }
                            }
                            children.add(child);
                            break;
                        default:
                            addProperty(type, record.getHead().replace(type.getTag(), "").replace("@", "").replace("P", "").trim());
                    }
                } catch(NoPropertyExistsException ex2) {
                    System.out.println("FAMILY - " + record.getHead() + " (" + record.size() + ")"); //TODO: Remove debug message
                }
            }
        }
    }

    @Override
    public void debug(String prefix) {
        System.out.println("- NEW FAMILY:");
        super.debug("   ");
        if(!events.isEmpty()) {
            System.out.println(prefix + "   - EVENTS:");
            for(Map.Entry<EventType, Event> event : events.entrySet()) {
                System.out.println(prefix + "      - " + event.getKey().name() + ": ");
                event.getValue().debug(prefix + "         ");
            }
        }
        if(!children.isEmpty()) {
            System.out.println(prefix + "   - CHILDREN:");
            for(Property child : children) {
                child.debug(prefix + "      ");
            }
        }
    }

    public String getID() {
        try {
            return getPropertyValue(FamilyProperty.ID);
        } catch (PropertyNotSetException ex) {
            return "";
        }
    }

    public List<Property> getChildren() {
        return Collections.unmodifiableList(children);
    }
}
