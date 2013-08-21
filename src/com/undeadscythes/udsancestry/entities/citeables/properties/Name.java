package com.undeadscythes.udsancestry.entities.citeables.properties;

import com.undeadscythes.udsancestry.entities.EntityProperty;
import com.undeadscythes.udsancestry.entities.citeables.properties.NamedProperty;
import com.undeadscythes.udsancestry.entities.citeables.properties.Property;
import com.undeadscythes.udsancestry.entities.citeables.individuals.IndividualProperty;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;

/**
 * @author UndeadScythes
 */
public class Name extends Property {
    private String family = "";
    private String given = "";

    public Name(RecordSchedule rs) {
        super(new NamedProperty("NAME"), rs.getHeader().replace("NAME", "").trim());
        String[] nameSplit = rs.getHeader().replace("NAME", "").trim().split("/");
        given = nameSplit[0].trim();
        if(nameSplit.length > 1) {
            family = nameSplit[1].trim();
        }
        while(rs.hasNext()) {
            Record record = rs.getRecord();
            if(record.headBegins("SOUR")) {
                addCitation(rs.nextLevel(), record);
            }
        }
    }

    @Override
    public void debug(String prefix) {
        System.out.println(prefix + "- NAME: " + family + ", " + given);
        System.out.println(prefix + "- ACTUAL VALUE:");
        super.debug(prefix + "   ");
    }

    @Override
    public EntityProperty getType() {
        return IndividualProperty.NAME;
    }

    public String getFullName() {
        return (given.isEmpty() ? "" : given + " ") + family;
    }
}
