package com.undeadscythes.supergenes.family;

import com.undeadscythes.supergenes.individual.*;
import com.undeadscythes.tipscript.*;
import static java.util.Collections.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class FamilyGroup {
    public Individual mother = Nobody.UNKNOWN;
    public Individual father = Nobody.UNKNOWN;
    public final Individual target;
    public List<Individual> siblings = new ArrayList<Individual>(0);
    public HashMap<Individual, List<Individual>> children = new HashMap<Individual, List<Individual>>(0);

    /**
     *
     * @param target
     */
    public FamilyGroup(Individual target) {
        this.target = target;
    }

    /**
     *
     * @param out
     */
    public void print(TipScript out) {
        if (mother.getID() + father.getID() > 0) {
            out.println("Parents:");
            if (father.getID() > 0) {
                out.println("- " + father.getFullName() + " (" + father.getID() + ")");
            }
            if (mother.getID() > 0) {
                out.println("- " + mother.getFullName() + " (" + mother.getID() + ")");
            }
        }
        out.println("Siblings:");
        sort(siblings, new SortByBirth(true));
        for (Individual i : siblings) {
            if (i.equals(target)) {
                out.println("- " + i.getFullName() + " (!)");
            } else {
                out.println("- " + i.getFullName() + " (" + i.getID() + ")");
            }
        }
        if (!children.isEmpty()) {
            out.println("Spouses and children:");
            for (Map.Entry<Individual, List<Individual>> l : children.entrySet()) {
                out.println("- " + l.getKey().getFullName() + " (" + l.getKey().getID() + ")");
                for (Individual i : l.getValue()) {
                    out.println("  - " + i.getFullName() + " (" + i.getID() + ")");
                }
            }
        }
    }
}
