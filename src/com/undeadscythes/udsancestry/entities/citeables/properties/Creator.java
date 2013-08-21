package com.undeadscythes.udsancestry.entities.citeables.properties;

import com.undeadscythes.udsancestry.entities.citeables.properties.Property;
import com.undeadscythes.udsancestry.entities.gedcom.GEDCOMProperty;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;

/**
 * @author UndeadScythes
 */
public class Creator extends Property {
    private String source;
    private String version;
    private String name;
    private String corporation;

    public Creator(RecordSchedule rs) {
        super(GEDCOMProperty.CREATOR, rs.getHeader().replace("SOUR", "").trim());
        source = rs.getHeader().replace("SOUR", "").trim();
        while(rs.hasNext()) {
            Record record = rs.getRecord();
            if(record.headBegins("VERS")) {
                version = record.getHead().replace("VERS", "").trim();
            } else if(record.headBegins("NAME")) {
                name = record.getHead().replace("NAME", "").trim();
            } else if(record.headBegins("CORP")) {
                corporation = record.getHead().replace("CORP", "").trim();
            }
        }
    }
}
