package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import com.undeadscythes.genebase.specific.*;
import com.undeadscythes.metaturtle.*;

/**
 * @author UndeadScythes
 */
public class FixMarr extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        for (final UniqueMeta holder : geneBase.getUniqueMeta(GEDTag.INDI)) {
            if (holder.getData("marr").size() != 1 || holder.getData("fams").size() != 1) continue;
            for (final Family family: ((Individual)holder).getFamilies()) {
                if (!Relation.PARENT.contains(family.getRelation(holder.getUID())) || family.getData("marr").size() > 0) continue;
                family.add(holder.getData(GEDTag.MARR.getTag()).get(0));
                holder.remove(GEDTag.MARR.getTag());
            }
        }
        out.println("All individual marriages associated with families.");
        return true;
    }
}
