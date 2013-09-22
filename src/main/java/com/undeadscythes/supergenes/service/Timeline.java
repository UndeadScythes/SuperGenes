package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.comparator.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import com.undeadscythes.genebase.structure.*;
import com.undeadscythes.metaturtle.metadata.*;
import com.undeadscythes.metaturtle.unique.*;
import com.undeadscythes.supergenes.exception.*;
import java.util.*;

/**
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
                } catch (TagNotSetException ex) {}
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
            } catch (TagNotSetException ex) {}
            String place = "";
            try {
                place = event.getPlace().toString();
            } catch (TagNotSetException ex) {}
            out.println("- " + date + " " + event.getValue() + (place.isEmpty() ? "" : " at " + place));
        }
        return true;
    }
}
