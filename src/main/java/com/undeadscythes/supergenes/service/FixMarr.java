package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.gedcom.GEDTag.Tag;
import com.undeadscythes.genebase.gedcom.RecordType;
import com.undeadscythes.genebase.record.Family;
import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.genebase.specific.Relation;
import com.undeadscythes.genebase.structure.Event;
import com.undeadscythes.metaturtle.exception.NoMetadataSetException;
import com.undeadscythes.metaturtle.unique.UniqueMeta;

/**
 * Fixes {@link Tag#MARR marriage}
 * {@link Event events} where the marriage
 * is stored with an {@link Individual individuals} record and could be placed
 * in an obvious {@link Family family} record.
 *
 * @author UndeadScythes
 */
public class FixMarr extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        for (final UniqueMeta holder : geneBase.getUniqueMeta(RecordType.INDI)) {
            try {
                if (holder.getList(Tag.MARR.getGEDTag()).size() != 1 || holder.getList(Tag.FAMS.getGEDTag()).size() != 1) continue;
                for (final Family family: ((Individual)holder).getFamilies()) {
                    if (!Relation.PARENT.contains(family.getRelation(holder.getUID())) || family.getList(Tag.MARR.getGEDTag()).size() > 0) continue;
                    family.add(holder.getList(Tag.MARR.getGEDTag()).get(0));
                    holder.remove(Tag.MARR.getGEDTag().toString());
                }
            } catch (NoMetadataSetException ex) {}
        }
        out.println("All individual marriages associated with families.");
        return true;
    }
}
