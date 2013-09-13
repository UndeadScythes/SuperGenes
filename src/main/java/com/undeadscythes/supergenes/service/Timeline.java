package com.undeadscythes.supergenes.service;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.event.*;
import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.supergenes.gedcom.*;
import com.undeadscythes.supergenes.holder.*;
import com.undeadscythes.supergenes.individual.*;
import static java.util.Collections.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Timeline extends AncestryService {
    public boolean run(String[] args) {
        Individual i = getIndividual(args);
        if (args.length == 0 && i.getID() == 0) {
            if (!out.openFile(gedcom.getName() + ".tl")) {
                out.println("Cannot open file.");
                return true;
            }
            List<Event> events = new ArrayList<Event>(0);
            for (Holder holder : gedcom.get(GEDCOMTag.INDIVIDUAL)) {
                for (Holder event : holder.getList(TagType.EVENT)) {
                    Event ev = (Event)event;
                    ev.link =(Individual)holder;
                    events.add(ev);
                }
            }
            sort(events, new SortByDate(true));
            for (Event event : events) {
                String date = "0000-000-00 ";
                try {
                    date = event.getDate().getString();
                } catch (TagNotSetException ex) {}
                String place = "";
                try {
                    place = event.getPlace();
                } catch (TagNotSetException ex) {}
                out.printf(date + " " + event.link.getFullName() + ": " + event.getTag().getFriendly() + (place.isEmpty() ? "" : " at " + place));
            }
            out.closeFile();
            out.println("Timeline saved to " + gedcom.getName() + ".tl.");
            return true;
        }
        if (i.getID() == 0) {
            out.println("Cannot find individual.");
        }
        out.println(i.getFullName() + "'s timeline:");
        for (Holder holder : i.getList(TagType.EVENT, new SortByDate(true))) {
            Event event = (Event)holder;
            String date = "0000-000-00 ";
            try {
                date = event.getDate().getString();
            } catch (TagNotSetException ex) {}
            String place = "";
            try {
                place = event.getPlace();
            } catch (TagNotSetException ex) {}
            out.println("- " + date + " " + event.getTag().getFriendly() + (place.isEmpty() ? "" : " at " + place));
        }
        return true;
    }
}
