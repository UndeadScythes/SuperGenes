package com.undeadscythes.udsancestry.entities.citeables.repositories;

import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;
import com.undeadscythes.udsancestry.exceptions.PropertyNotSetException;
import com.undeadscythes.udsancestry.record.Record;
import com.undeadscythes.udsancestry.record.RecordSchedule;
import com.undeadscythes.udsancestry.entities.citeables.Citeable;

/**
 * @author UndeadScythes
 */
public class Repository extends Citeable {
    public Repository(RecordSchedule rs) {
        addProperty(RepositoryProperty.ID, rs.getHeader().replace("@R", "").replace("@ REPO", "").trim());
        Record record = rs.getRecord();
        for(String line : record) {
            try {
                RepositoryProperty type = RepositoryProperty.getByTag(line.split(" ")[0]);
                addProperty(type, line.replace(type.getTag(), "").trim());
            } catch(NoPropertyExistsException ex) {
                System.out.println("REPOSITORY - " + line); //TODO: Remove debug message
            }
        }
    }

    @Override
    public void debug(String prefix) {
        System.out.println("- NEW REPOSITORY:");
        super.debug("   ");
    }

    public String getID() {
        try {
            return getPropertyValue(RepositoryProperty.ID);
        } catch (PropertyNotSetException ex) {
            return "";
        }
    }
}
