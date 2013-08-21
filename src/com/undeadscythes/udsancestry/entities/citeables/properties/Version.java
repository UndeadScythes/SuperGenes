package com.undeadscythes.udsancestry.entities.citeables.properties;

import com.undeadscythes.udsancestry.entities.citeables.properties.Property;
import com.undeadscythes.udsancestry.entities.gedcom.GEDCOMProperty;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;

/**
 * @author UndeadScythes
 */
public class Version extends Property {
    private String version;
    private String form;

    public Version(RecordSchedule rs) {
        super(GEDCOMProperty.VERSION, rs.getHeader().replace("GEDC", "").trim());
        while(rs.hasNext()) {
            Record record = rs.getRecord();
            if(record.headBegins("VERS")) {
                version = record.getHead().replace("VERS", "").trim();
            } else if(record.headBegins("FORM")) {
                form = record.getHead().replace("FORM", "").trim();
            }
        }
    }
}
