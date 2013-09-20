package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.comparator.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import com.undeadscythes.metaturtle.*;
import static java.util.Collections.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Find extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        final List<Individual> matches = new ArrayList<Individual>(0);
        for (UniqueMeta holder : geneBase.getUniqueMeta(GEDTag.INDI)) {
            final Individual indi = (Individual)holder;
            if ((args[0].isEmpty() && indi.getFamilyName().isEmpty()) || (!args[0].isEmpty() && indi.getFamilyName().toLowerCase().startsWith(args[0].toLowerCase()))) {
                matches.add(indi);
            }
        }
        if (matches.isEmpty()) {
            out.println("No matches found.");
            return true;
        }
        sort(matches, SortByName.ASCENDING);
        for (Individual indi : matches) {
            final String name = indi.getFamilyName();
            out.println("- " + (name.isEmpty() ? "" : name + ", ") + indi.getGivenName() + " (" + indi.getUID() + ")");
        }
        return true;
    }
}
