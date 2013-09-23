package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import com.undeadscythes.metaturtle.unique.*;
import com.undeadscythes.supergenes.validator.*;
import java.util.*;

/**
 * Display the direct relations of an {@link Individual individual}.
 *
 * @author UndeadScythes
 */
public class Relations extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        if (args.length == 0) {
            final IndividualValidator val = new IndividualValidator(geneBase);
            while (!program.getResponse(val, "Enter an ID number: ", "Cannot find individual.").isEmpty()) {
                showRelations(val.getValidIndi());
            }
        } else {
            final Individual indi = getIndividual(args);
            if (indi.getUID().isNull()) {
                out.println("Cannot find individual.");
            } else {
                showRelations(indi);
            }
        }
        return true;
    }

    private void showRelations(final Individual indi) {
        final List<Family> families = new ArrayList<Family>(0);
        for (UniqueMeta meta : geneBase.getUniqueMeta(RecordType.FAM)) {
            final Family family = (Family)meta;
            if (family.hasMember(indi.getUID())) {
                families.add(family);
            }
        }
        if (families.isEmpty()) {
            out.println("No direct relations found.");
        }
        for (Family family : families) {
            out.print(family.print());
        }
    }
}
