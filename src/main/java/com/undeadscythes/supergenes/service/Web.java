package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.supergenes.service.*;
import com.undeadscythes.supergenes.web.CSS;
import com.undeadscythes.supergenes.web.Index;
import java.io.*;
//import com.undeadscythes.supergenes.OLD.*;
//import com.undeadscythes.supergenes.event.*;
//import com.undeadscythes.supergenes.exception.*;
//import com.undeadscythes.supergenes.family.*;
//import com.undeadscythes.supergenes.individual.*;
//import com.undeadscythes.tipscript.*;
//import static java.util.Collections.*;

/**
 * The SuperGenesWebify {@link com.undeadscythes.authenticmd.service.Service}
 * will create a website from the data in a given
 * {@link com.undeadscythes.genebase.GeneBase}.
 *
 * @author UndeadScythes
 */
public class Web extends AncestryService {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean run(final String[] args) {
        final String rootDir = "www-" + geneBase.getUID();
        //new File(rootDir).mkdir();
        if(!new CSS().publish(rootDir, geneBase)) {
            out.println("Cannot write CSS to file.");
            return true;
        }
        new Index().publish(rootDir, geneBase);
//        publishDirectory();
//        publishIndis();
//        out.println("Website written to www-" + gedcom.getName() + ".");
        return true;
    }

//    private void publishDirectory() {
//        final HyperForm form = new HyperForm();
//        resetCitations();
//        form.load("title", gedcom.getName() + " Directory");
//        form.load("prefix", "");
//        form.load("h1", gedcom.getName());
//        form.load("h2", "Directory");
//        form.load("directory", getDirectory());
//        form.load("main", new HyperOp(HyperType.PARSE, "/web/directory.html"));
//        form.publish(form.parse(getClass().getResourceAsStream("/web/layout.html")), new File(rootDir + "/directory.html"));
//    }

//    private String getDirectory() {
//        final StringBuilder output = new StringBuilder("");
//        final List<Individual> indi = new ArrayList<Individual>(0);
//        for (UniqueHolder holder : gedcom.get(GEDCOMTag.INDI)) {
//            indi.add((Individual)holder);
//        }
//        sort(indi, new SortByName(true));
//        String current = "#";
//        for (Individual i : indi) {
//            if (!i.getFamilyName().isEmpty()) {
//                final String thisA = i.getFamilyName().substring(0, 1).toUpperCase();
//                if (thisA.compareTo(current) > 0) {
//                    output.append("</ul></li><li><a id='" + thisA + "'>"+ thisA + "</a><ul>");
//                    current = thisA;
//                }
//            }
//            output.append("<li><a href='indi/").append(i.getID()).append(".html'>").append((i.getFamilyName().isEmpty() ? "" : i.getFamilyName() + ", ") + i.getGivenName()).append("</a></li>");
//        }
//        return output.toString();
//    }

//    private void publishIndis() {
//        final String indiDir = rootDir + "/indi";
//        new File(indiDir).mkdir();
//        final HyperForm form = new HyperForm();
//        final HyperOp parse = new HyperOp(HyperType.PARSE, "/web/indi.html");
//        for (UniqueHolder holder : gedcom.get(GEDCOMTag.INDI)) {
//            final Individual indi = (Individual)holder;
//            resetCitations();
//            form.load("title", indi.getFamilyName());
//            form.load("prefix", "../");
//            form.load("h1", indi.getFamilyName());
//            form.load("h2", indi.getGivenName());
//            form.load("namecitation", getCitations(indi));
//            form.load("indi", getIndi(indi));
//            form.load("main", parse);
//            form.publish(form.parse(getClass().getResourceAsStream("/web/layout.html")), new File(indiDir + "/" + indi.getID() + ".html"));
//        }
//        resetCitations();
//        form.load("title", Nobody.UNKNOWN.getFamilyName());
//        form.load("prefix", "../");
//        form.load("h1", Nobody.UNKNOWN.getFamilyName());
//        form.load("h2", Nobody.UNKNOWN.getGivenName());
//        form.load("indi", getIndi(Nobody.UNKNOWN));
//        form.load("main", parse);
//        form.publish(form.parse(getClass().getResourceAsStream("/web/layout.html")), new File(indiDir + "/" + Nobody.UNKNOWN.getID() + ".html"));
//    }

//    private String getIndi(final Individual indi) {
//        final StringBuilder output = new StringBuilder("");
//        output.append("<h3>Family</h3><ul>");
//        for (final Family family : indi.getFamilies()) {
//            if (family.getRelation(indi.getID()).equals(Relation.CHILD)) output.append(getParents(family, indi));
//        }
//        for (Family family : indi.getFamilies()) {
//            if (Relation.PARENT.contains(family.getRelation(indi.getID()))) {
//                output.append(getChildren(family, indi));
//            }
//        }
//        output.append("</ul><h3>Facts</h3><ul>");
//        for (Holder holder : indi.getList(GEDCOMTagType.FACT_CUSTOM)) {
//            if (skipTags().contains(holder.getTag())) continue;
//            output.append(getData(holder));
//        }
//        output.append("</ul><h3>Events</h3><ul>");
//        final ArrayList<Event> events = new ArrayList<Event>(0);
//        for (Holder holder : indi.getList(GEDCOMTagType.EVENT)) {
//            events.add((Event)holder);
//        }
//        sort(events, new SortByDate(true));
//        for (Event event : events) {
//            output.append(getEvents(event));
//        }
//        output.append("</ul><h3>Sources</h3><ul>");
//        for (Map.Entry<Integer, Holder> citation : citations.entrySet()) {
//            output.append("<li><a id='" + citation.getKey() + "'></a>[" + citation.getKey() + "]");
//            final Holder holder = citation.getValue();
//            if (!holder.getList(GEDCOMTagType.FACT_CUSTOM).isEmpty()) {
//                output.append("<ul>");
//                for (Holder fact : holder.getList(GEDCOMTagType.FACT_CUSTOM)) {
//                    output.append(getData(fact));
//                }
//                output.append("</ul>");
//            }
//            if (!(holder instanceof Source) || ((Source)holder).getCitations().size() < 2) continue;
//            output.append("<li>Other people on source:<ul>");
//            for (Holder cited : ((Source)holder).getCitations()) {
//                if (cited instanceof Individual) {
//                    output.append("<li>").append("<a href='" + ((Individual)cited).getID() + ".html'>" + ((Individual)cited).getFullName() + "</a>").append("</li>");
//                }
//            }
//            output.append("</ul></li>");
//        }
//        output.append("</ul></div><hr><div id='dump'><h3>GEDCOM Dump</h3><pre>");
//        indi.dump(new TipRedirect(output), "- ");
//        for (final Family family : indi.getFamilies()) {
//            family.dump(new TipRedirect(output), "- ");
//        }
//        output.append("</pre></div>");
//        return output.toString();
//    }

//    private String getEvents(final Event event) {
//        final StringBuilder output = new StringBuilder("");
//        String date = "0000-000-00 ";
//        try {
//            date = event.getDate().getString();
//        } catch (TagNotSetException ex) {}
//        String place = "";
//        try {
//            place = event.getPlace();
//        } catch (TagNotSetException ex) {}
//        output.append("<li>" + event.getTag().getFriendly() + ": " + date + (place.isEmpty() ? "" : " at " + place));
//        output.append(getCitations(event));
//        if (!event.getList(GEDCOMTagType.FACT_CUSTOM).isEmpty()) {
//            output.append("<ul>");
//            for (Holder fact : event.getList(GEDCOMTagType.FACT_CUSTOM)) {
//                if (fact.getTag().equals(GEDCOMTag.DATE) || fact.getTag().equals(GEDCOMTag.PLAC)) continue;
//                output.append(getData(fact));
//            }
//            output.append("</ul>");
//        }
//        output.append("</li>");
//        return output.toString();
//    }

//    private String getParents(final Family family, final Individual indi) {
//        final StringBuilder output = new StringBuilder("");
//        output.append("<li>Parents:<ul>");
//        output.append("<li><a href='" + family.getFather().getID() + ".html'>" + family.getFather().getFullName() + "</a></li>");
//        output.append("<li><a href='" + family.getMother().getID() + ".html'>"  + family.getMother().getFullName() + "</a></li></ul></li>");
//        if (family.getChildren().size() > 1) {
//            output.append("<li>Siblings:<ul>");
//            for (Individual s : family.getChildren()) {
//                if (indi.getID() == s.getID()) {
//                    output.append("<li>" + s.getFullName() + "</li>");
//                    continue;
//                }
//                output.append("<li><a href='" + s.getID() + ".html'>" + s.getFullName() + "</a></li>");
//            }
//            output.append("</ul></li>");
//        }
//        return output.toString();
//    }

//    private String getChildren(final Family family, final Individual indi) {
//        final StringBuilder output = new StringBuilder("<li>");
//        output.append(family.getMaritalStatus(indi.getID()).getTitle()).append(": ");
//        if (indi.getID().equals(family.getFather().getID())) {
//            output.append("<a href='" + family.getMother().getID() + ".html'>" + family.getMother().getFullName() + "</a>");
//        } else {
//            output.append("<a href='" + family.getFather().getID() + ".html'>" + family.getFather().getFullName() + "</a>");
//        }
//        output.append(getCitations(family)).append("<ul>");
//        for (Holder fact : family.getList(GEDCOMTagType.FACT_CUSTOM)) {
//            if (skipTags().contains(fact.getTag())) continue;
//            output.append(getData(fact));
//        }
//        for (Holder event : family.getList(GEDCOMTagType.EVENT)) {
//            output.append(getEvents((Event)event));
//        }
//        if (family.getChildren().size() > 0) {
//            output.append("<li>Children:<ul>");
//            for (Individual s : family.getChildren()) {
//                output.append("<li><a href='"  + s.getID() + ".html'>"+ s.getFullName() + "</a></li>");
//            }
//            output.append("</ul>");
//        }
//        output.append("</li></ul></li>");
//        return output.toString();
//    }
}
