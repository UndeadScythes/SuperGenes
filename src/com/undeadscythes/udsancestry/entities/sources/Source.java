package com.undeadscythes.udsancestry.entities.sources;

import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;
import com.undeadscythes.udsancestry.exceptions.PropertyNotSetException;
import com.undeadscythes.udsancestry.entities.Entity;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;

/**
 * @author UndeadScythes
 */
public class Source extends Entity {
    public Source(RecordSchedule rs) {
        addProperty(SourceProperty.ID, rs.getHeader().replace("@ SOUR", "").replace("@S", "").trim());
        while(rs.hasNext()) {
            Record record = rs.getRecord();
            for(String line : record) {
                try {
                    String[] split = line.split(" ");
                    String tag = split[0];
                    if(tag.matches("[0-9]*")) {
                        tag = split[1];
                    }
                    SourceProperty type = SourceProperty.getByTag(tag);
                    if(type.equals(SourceProperty.REPOSITORY)) {
                        addProperty(type, line.replace(tag, "").replace("R", "").replace("@", "").trim());
                    } else {
                        addProperty(type, line.replace(tag, "").trim());
                    }
                } catch(NoPropertyExistsException ex) {
                    if(!line.contains("DATA")) {
                        System.out.println("SOURCE - " + line); //TODO: Remove debug message
                    }
                }
            }
        }
    }

    @Override
    public void debug(String prefix) {
        System.out.println(prefix + "- NEW SOURCE");
        super.debug(prefix + "   ");
    }

    public String getID() {
        try {
            return getPropertyValue(SourceProperty.ID);
        } catch (PropertyNotSetException ex) {
            return "";
        }
    }
}
