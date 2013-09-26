package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.comparator.SortByDate;
import com.undeadscythes.genebase.gedcom.GEDTag;
import com.undeadscythes.genebase.gedcom.RecordType;
import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.genebase.structure.Event;
import com.undeadscythes.genebase.structure.Place;
import com.undeadscythes.metaturtle.exception.NoMetadataSetException;
import com.undeadscythes.metaturtle.metadata.Metadata;
import com.undeadscythes.metaturtle.unique.UniqueMeta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Display the {@link Event events} associated with an
 * {@link Individual individual} in chronological order.
 *
 * @author UndeadScythes
 */
public class Timeline extends AncestryService {
    public boolean run(final String[] args) {
        final Individual indi = getIndividual(args);
        if (args.length == 0 && indi.getUID().isNull()) {
            if (!out.openFile(geneBase.getUID() + ".tl")) {
                out.println("Cannot open file.");
                return true;
            }
            final List<Event> events = new ArrayList<Event>(0);
            for (UniqueMeta holder : geneBase.getUniqueMeta(RecordType.INDI)) {
                for (Metadata hol : holder.getList(GEDTag.EVEN)) {
                    final Event eve = (Event)hol;
                    events.add(eve);
                }
            }
            Collections.sort(events, SortByDate.INCREASING);
            for (Event event : events) {
                String date = "0000-000-00 ";
                try {
                    date = event.getDate().toString();
                } catch (NoMetadataSetException ex) {}
                final Place place = event.getPlace();
                out.printf(date + " " + event.getValue() + (place.isEmpty() ? "" : " at " + place));
            }
            out.closeFile();
            out.println("Timeline saved to " + geneBase.getUID() + ".tl.");
            return true;
        }
        if (indi.getUID().isNull()) {
            out.println("Cannot find individual.");
        }
        out.println(indi.getFullName() + "'s timeline:");

        for (Metadata holder : indi.getSortedList(GEDTag.EVEN, SortByDate.INCREASING)) {
            final Event event = (Event)holder;
            String date = "0000-000-00 ";
            try {
                date = event.getDate().toString();
            } catch (NoMetadataSetException ex) {}
            String place = "";
            place = event.getPlace().toString();
            out.println("- " + date + " " + event.getValue() + (place.isEmpty() ? "" : " at " + place));
        }
        return true;
    }
}
