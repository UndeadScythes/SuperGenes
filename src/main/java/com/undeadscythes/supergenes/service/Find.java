package com.undeadscythes.supergenes.service;

import com.undeadscythes.supergenes.gedcom.*;
import com.undeadscythes.supergenes.holder.*;
import com.undeadscythes.supergenes.individual.*;
import static java.util.Collections.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Find extends AncestryService {
    @Override
    public boolean run(String[] args) {
        List<Individual> matches = new ArrayList<Individual>(0);
        for (UniqueHolder holder : gedcom.get(GEDCOMTag.INDIVIDUAL)) {
            Individual i = (Individual)holder;
            if ((args[0].isEmpty() && i.getFamilyName().isEmpty()) || (!args[0].isEmpty() && i.getFamilyName().toLowerCase().startsWith(args[0].toLowerCase()))) {
                matches.add(i);
            }
        }
        if (matches.isEmpty()) {
            out.println("No matches found.");
            return true;
        }
        sort(matches, new SortByName(true));
        for (Individual i : matches) {
            String name = i.getFamilyName();
            out.println("- " + (name.isEmpty() ? "" : name + ", ") + i.getGivenName() + " (" + i.getID() + ")");
        }
        return true;
    }
}
