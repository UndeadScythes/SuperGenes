package com.undeadscythes.udsancestry.entities.citeables;

import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;

/**
 * @author UndeadScythes
 */
public class Citation extends Citeable {
    public Citation(RecordSchedule rs) {
        addProperty(CitationProperty.ID, rs.getHeader().replace("SOUR", "").replace("@", "").replace("S", "").trim());
        Record record = rs.getRecord();
        for(String line : record) {
            try {
                String[] split = line.split(" ");
                String tag = split[0];
                if(tag.matches("[0-9]*")) {
                    tag = split[1];
                }
                CitationProperty type = CitationProperty.getByTag(tag);
                addProperty(type, line.replace(tag, "").trim());
            } catch(NoPropertyExistsException ex) {
                if(!line.contains("DATA")) {
                    System.out.println("CITATION - " + line); //TODO: Remove debug message
                }
            }
        }
    }
}
