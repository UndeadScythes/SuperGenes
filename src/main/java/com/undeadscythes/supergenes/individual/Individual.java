package com.undeadscythes.supergenes.individual;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.supergenes.holder.*;

/**
 * @author UndeadScythes
 */
public class Individual extends UniqueHolder {
    /**
     *
     * @param record
     */
    public Individual(final Record record) {
        set(record);
    }

    /**
     *
     * @return
     */
    public String getFullName() {
        try {
            return getValue(IndividualTag.NAME).replace("/", "");
        } catch (TagNotSetException ex) {
            return "<No Name>";
        }
    }

    /**
     *
     * @return
     */
    public String getFamilyName() {
        String name = getValue(IndividualTag.NAME);
        if (name.indexOf('/') == -1) return "";
        return name.substring(name.indexOf('/') + 1, name.length() - 1);
    }

    /**
     *
     * @return
     */
    public String getGivenName() {
        String name = getValue(IndividualTag.NAME);
        if (name.indexOf('/') == -1) return name;
        if (name.indexOf('/') == 0) return name.replace("/", "");
        return name.substring(0, name.indexOf('/') - 1);
    }

    /**
     *
     * @return
     */
    public Gender getGender() {
        try {
            String gender = getValue(IndividualTag.GENDER);
            if (gender.equals("M")) return Gender.MALE;
            if (gender.equals("F")) return Gender.FEMALE;
        } catch (TagNotSetException ex) {}
        return Gender.UNKNOWN;
    }
}
