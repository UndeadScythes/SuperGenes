package com.undeadscythes.supergenes.service;

import com.undeadscythes.supergenes.family.*;
import com.undeadscythes.supergenes.gedcom.*;
import com.undeadscythes.supergenes.holder.*;
import com.undeadscythes.supergenes.individual.*;
import static java.lang.Integer.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Relations extends AncestryService {
    @Override
    public boolean run(String[] args) {
        Individual i = getIndividual(args);
        if (args.length == 0 && i.getID() == 0) {
            String get;
            do {
                get = program.getResponse(null, "Enter an ID number: ", "Cannot find individual.");
                if (get.matches("[0-9]+")) {
                    i = (Individual)gedcom.get(GEDCOMTag.INDIVIDUAL, parseInt(get));
                    showRelations(i);
                }
            } while (!get.toLowerCase().startsWith("q"));
            return true;
        }
        if (i.getID() == 0) {
            out.println("Cannot find individual.");
            return true;
        }
        showRelations(i);
        return true;
    }

    private void showRelations(Individual i) {
        List<Family> fams = new ArrayList<Family>(0);
        for (Holder holder : gedcom.get(GEDCOMTag.FAMILY)) {
            Family fam = (Family)holder;
            if (fam.contains(i.getID())) {
                fams.add(fam);
            }
        }
        if (fams.isEmpty()) {
            out.println("No direct relations found.");
        }
        FamilyGroup group = new FamilyGroup(i);
        for (Family family : fams) {
            family.addTo(group);
        }
        group.print(out);
    }
}
