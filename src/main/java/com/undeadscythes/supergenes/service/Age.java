package com.undeadscythes.supergenes.service;

import com.undeadscythes.supergenes.event.*;
import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.supergenes.individual.*;
import static java.lang.Integer.*;
import java.util.*;
import static java.util.Calendar.*;

/**
 * @author UndeadScythes
 */
public class Age extends AncestryService {
    @Override
    public boolean run(String[] args) {
        Individual i = getIndividual(args);
        if (i.getID() == 0) {
            out.println("Cannot find individual.");
            return true;
        }
        int year = getInstance().get(Calendar.YEAR);
        if (args.length == 2 && args[1].matches("[0-9]+")) {
            year = parseInt(args[1]);
        }
        try {
            Event event = (Event)i.getTag(IndividualTag.BIRTH);
            int birth = event.getDate().year;
            if (birth == 0) throw new TagNotSetException(IndividualTag.BIRTH);
            out.println(i.getFullName() + "'s age in " + year + " is ~" + (year - birth) + ".");

        } catch (TagNotSetException ex) {
            out.println("Unknown age, no birth year recorded.");
        }
        return true;
    }
}
