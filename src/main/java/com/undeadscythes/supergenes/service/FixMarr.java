package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import com.undeadscythes.genebase.specific.*;
import com.undeadscythes.metaturtle.unique.*;

/**
 * @author UndeadScythes
 */
public class FixMarr extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        for (final UniqueMeta holder : geneBase.getUniqueMeta(RecordType.INDI)) {
            if (holder.getList(GEDTag.MARR).size() != 1 || holder.getList(GEDTag.FAMS).size() != 1) continue;
            for (final Family family: ((Individual)holder).getFamilies()) {
                if (!Relation.PARENT.contains(family.getRelation(holder.getUID())) || family.getList(GEDTag.MARR).size() > 0) continue;
                family.add(holder.getList(GEDTag.MARR).get(0));
                holder.remove(GEDTag.MARR.getString());
            }
        }
        out.println("All individual marriages associated with families.");
        return true;
    }
}
