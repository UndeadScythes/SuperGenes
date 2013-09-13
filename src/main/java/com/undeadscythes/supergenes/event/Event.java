package com.undeadscythes.supergenes.event;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.holder.*;
import com.undeadscythes.supergenes.individual.*;

/**
 * @author UndeadScythes
 */
public class Event extends Holder {
    /**
     *
     */
    public Individual link;

    /**
     *
     * @param record
     */
    public Event(final Record record) {
        super();
        set(record);
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return new Date(getValue(EventTag.DATE));
    }

    /**
     *
     * @param date
     */
    public void setDate(final Date date) {
        getTag(EventTag.DATE).setValue(date.getString().replace("-", " "));
    }

    /**
     *
     * @return
     */
    public String getPlace() {
        return getValue(EventTag.PLACE);
    }
}
