package com.undeadscythes.supergenes.individual;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.gedcom.*;

/**
 * @author UndeadScythes
 */
public class Nobody extends Individual {
    /**
     *
     */
    public static final Individual NOBODY;
    /**
     *
     */
    public static final Individual UNKNOWN;

    static {
        Record record = new Record();
        record.add(new LineStruct(0, GEDCOMTag.INDIVIDUAL, "@I0@"));
        record.add(new LineStruct(1, IndividualTag.NAME, "/Nobody/"));
        NOBODY = new Nobody(record);
        record = new Record();
        record.add(new LineStruct(0, GEDCOMTag.INDIVIDUAL, "@I0@"));
        record.add(new LineStruct(1, IndividualTag.NAME, "/Unknown/"));
        UNKNOWN = new Nobody(record);
    }

    /**
     *
     * @param record
     */
    public Nobody(Record record) {
        super(record);
    }
}
