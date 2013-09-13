package com.undeadscythes.supergenes.service;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.event.*;
import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.supergenes.family.*;
import com.undeadscythes.supergenes.gedcom.*;
import com.undeadscythes.supergenes.holder.*;
import com.undeadscythes.supergenes.individual.*;
import java.io.*;
import static java.lang.Character.*;
import static java.lang.String.*;
import static java.util.Collections.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Web extends AncestryService {
    private HashMap<Integer, Holder> citations = new HashMap<Integer, Holder>(0);
    private HashMap<Integer, Integer> indexes = new HashMap<Integer, Integer>(0);
    private int index = 1;

    @Override
    public boolean run(String[] args) {
        File root = new File("www-" + gedcom.getName());
        root.mkdir();
        printIndex(root);
        printDirectory(root);
        printIndis(root);
        printCSS(root);
        out.println("Website written to www-" + gedcom.getName() + ".");
        return true;
    }

    /**
     *
     * @param root
     */
    public void printCSS(File root) {
        if (!out.openFile(root.getAbsolutePath() + "/stylesheet.css")) {
            out.println("Cannot write to file.");
            return;
        }
        out.printf("html {overflow-y: scroll;}");
        out.printf("body {cursor: default; text-align: center; width: 1000px; margin: 5px auto;}");
        out.printf("a {cursor: pointer;}");
        out.printf("#menu {}");
        out.printf("#menu ul {padding-left: 0;}");
        out.printf("#menu ul li {padding: 5px; display: inline;}");
        out.printf("h1 {text-align:center;}");
        out.printf("h2 {text-align:center; display: inline-block;}");
        out.printf("h3 {text-align:center; text-decoration: underline; margin: .5em auto}");
        out.printf("ul {padding-left: 1.1em; margin: 0 auto;}");
        out.printf("div {border-radius: 10px; margin: 1em auto;padding: 1em;}");
        out.printf("hr {width: 50%; margin: 0 auto;}");
        out.printf("#main {display: inline-block; text-align:left; background-color: palegreen; padding-top: 0;}");
        out.printf(".citation {font-size: 0.7em; vertical-align: super;}");
        out.printf("#dump {text-align:center; display: inline-block; background-color: salmon;}");
        out.printf("#dump pre {text-align:left;}");
        out.closeFile();
    }

    /**
     *
     * @param root
     */
    public void printIndex(File root) {
        if (!out.openFile(root.getAbsolutePath() + "/index.html")) {
            out.println("Cannot write to file.");
        }
        out.printf("<html><head><title>" + gedcom.getName() + " Home</title>");
        out.printf("<link rel='stylesheet' type='text/css' href='stylesheet.css'></head>");
        printMenu("");
        out.printf("<body><h1>" + gedcom.getName() + "</h1><h2>Home</h2><hr>");
        out.printf("<div id='main'><h3>GEDCOM Details</h3>");
        printHeader();
        out.printf("</div>");
        out.printf("</body></html>");
        out.closeFile();
    }

    /**
     *
     * @param prefix
     */
    public void printMenu(String prefix) {
        out.printf("<div id='menu'><ul><li><a href='" + prefix + "index.html'>Home</a></li>");
        out.printf("<li><a href='" + prefix + "directory.html'>Directory</a></li>");
        out.printf("<li><a href='" + prefix + "indi/1.html'>Root</a></li></ul></div><hr>");
    }

    /**
     *
     */
    public void printHeader() {
        out.printf("<ul>");
        for (Holder d : gedcom.getHeader().getList()) {
            out.printf("<li>" + d.getTag().getFriendly() + ": " + d.getValue());
            if (!d.getList(TagType.FACT).isEmpty()) {
                out.printf("<ul>");
                for (Holder h : d.getList(TagType.FACT)) {
                    printData(h);
                }
                out.printf("</ul>");
            }
        }
        out.printf("</ul>");
    }

    /**
     *
     * @param root
     */
    public void printDirectory(File root) {
        if (!out.openFile(root.getAbsolutePath() + "/directory.html")) {
            out.println("Cannot write to file.");
        }
        out.printf("<html><head><title>" + gedcom.getName() + " Directory</title>");
        out.printf("<link rel='stylesheet' type='text/css' href='stylesheet.css'></head>");
        printMenu("");
        out.printf("<body><h1>" + gedcom.getName() + "</h1><h2>Directory</h2><hr>");
        out.printf("<div id='main'><div><ul>");
        List<Individual> a = new ArrayList<Individual>(0);
        for (UniqueHolder u : gedcom.get(GEDCOMTag.INDIVIDUAL)) {
            a.add((Individual)u);
        }
        sort(a, new SortByName(true));
        String current = "#";
        for (int i = 0; i < 26; i++) {
            String c = valueOf(toChars(65 + i));
            out.printf("<a href='#" + c + "'>" + c + "</a>");
        }
        out.printf("<li>#<ul>");
        for (Individual i : a) {
            if (!i.getFamilyName().isEmpty()) {
                String thisA = i.getFamilyName().substring(0, 1).toUpperCase();
                if (thisA.compareTo(current) > 0) {
                    out.printf("</ul></li><li><a id='" + thisA + "'>"+ thisA + "</a><ul>");
                    current = thisA;
                }
            }
            out.printf("<li><a href='indi/" + i.getID() + ".html'>" + (i.getFamilyName().isEmpty() ? "" : i.getFamilyName() + ", ") + i.getGivenName() + "</a></li>");
        }
        out.printf("</ul></li></ul></div>");
        out.printf("</body></html>");
        out.closeFile();

    }

    /**
     *
     * @param root
     */
    public void printIndis(File root) {
        File indi = new File(root.getAbsolutePath() + "/indi");
        indi.mkdir();
        for (UniqueHolder u : gedcom.get(GEDCOMTag.INDIVIDUAL)) {
            Individual i = (Individual)u;
            citations = new HashMap<Integer, Holder>(0);
            indexes = new HashMap<Integer, Integer>(0);
            index = 1;
            if (!out.openFile(indi.getAbsolutePath() + "/" + i.getID() + ".html")) {
                out.println("Cannot write to file.");
            }
            out.printf("<html><head><title>" + i.getFamilyName() + "</title>");
            out.printf("<link rel='stylesheet' type='text/css' href='../stylesheet.css'></head>");
            printMenu("../");
            out.printf("<body><h1>" + i.getFamilyName() + "</h1>");
            out.printf("<h2>" + i.getGivenName() + "</h2>");
            printCitations(i);
            out.printf("<hr><div id='main'><h3>Family</h3>");
            for (Holder h : i.getList(TagType.XREF)) {
                if (h.getTag().equals(IndividualTag.FAMILY_CHILD)) {
                    out.printf("Parents:");
                    printParents((Family)gedcom.get(GEDCOMTag.FAMILY, h.parseID()), i);
                }
            }
            out.printf("<h3>Facts</h3>");
            for (Holder h : i.getList(TagType.FACT)) {
                out.printf("<ul>");
                printData(h);
                out.printf("</ul>");
            }
            out.printf("<h3>Events</h3>");
            ArrayList<Event> e = new ArrayList<Event>(0);
            for (Holder h : i.getList(TagType.EVENT)) {
                e.add((Event)h);
            }
            sort(e, new SortByDate(true));
            for (Event ev : e) {
                out.printf("<ul>");
                printEvent(ev);
                out.printf("</ul>");
            }
            out.printf("<h3>Descendants</h3><ul>");
            for (Holder h : i.getList(TagType.XREF)) {
                if (h.getTag().equals(IndividualTag.FAMILY_SPOUSE)) {
                    out.printf("<li>");
                    printChildren((Family)gedcom.get(GEDCOMTag.FAMILY, h.parseID()), i);
                    out.printf("</li>");
                }
            }
            out.printf("</ul><h3>Sources</h3>");
            out.printf("<ul>");
            for (Map.Entry<Integer, Holder> a : citations.entrySet()) {
                out.printf("<li><a id='" + a.getKey() + "'></a>[" + a.getKey() + "]");
                Holder d = a.getValue();
                if (d.getValue().toLowerCase().startsWith("http")) {
                    out.printf(d.getTag().getFriendly() + ": <a target='_blank' href='" + d.getValue() + "'>" + d.getValue() + "</a>");
                } else {
                    out.printf(d.getTag().getFriendly() + ": " + d.getValue());
                }
                if (!d.getList(TagType.FACT).isEmpty()) {
                    out.printf("<ul>");
                    for (Holder h : d.getList(TagType.FACT)) {
                        printData(h);
                    }
                    out.printf("</ul>");
                }
            }
            out.printf("</ul>");
            out.printf("</div></div><hr><div id='dump'><h3>GEDCOM Dump</h3><pre>");
            i.dump(out, "- ");
            out.printf("</pre></div></body></html>");
            out.closeFile();
        }
    }

    /**
     *
     * @param d
     */
    public void printData(Holder d) {
        if (d.getValue().toLowerCase().startsWith("http")) {
            out.printf("<li>" + d.getTag().getFriendly() + ": <a target='_blank' href='" + d.getValue() + "'>" + d.getValue() + "</a>");
        } else {
            out.printf("<li>" + d.getTag().getFriendly() + ": " + d.getValue());
        }
        if (!d.getList(TagType.FACT).isEmpty()) {
            out.printf("<ul>");
            for (Holder h : d.getList(TagType.FACT)) {
                printData(h);
            }
            out.printf("</ul>");
        }
        printCitations(d);
    }

    /**
     *
     * @param e
     */
    public void printEvent(Event e) {
        String date = "0000-000-00 ";
        try {
            date = e.getDate().getString();
        } catch (TagNotSetException ex) {}
        String place = "";
        try {
            place = e.getPlace();
        } catch (TagNotSetException ex) {}
        out.printf("<li>" + e.getTag().getFriendly() + ": " + date + (place.isEmpty() ? "" : " at " + place));
        printCitations(e);
        if (!e.getList(TagType.FACT).isEmpty()) {
            out.printf("<ul>");
            for (Holder h : e.getList(TagType.FACT)) {
                if (h.getTag().equals(EventTag.DATE) || h.getTag().equals(EventTag.PLACE)) continue;
                printData(h);
            }
            out.printf("</ul>");
        }
        out.printf("</li>");
    }

    /**
     *
     * @param f
     * @param i
     */
    public void printParents(Family f, Individual i) {
        out.printf("<a href='" + f.father.getID() + ".html'>" + f.father.getFullName() + "</a> = <a href='" + f.mother.getID() + ".html'>"  + f.mother.getFullName() + "</a><br>");
        if (f.children.values().size() > 1) {
            out.printf("Siblings:<ul>");
            for (Individual s : f.children.values()) {
                if (i.getID() == s.getID()) {
                    out.printf("<li>" + s.getFullName() + "</li>");
                    continue;
                }
                out.printf("<li><a href='" + s.getID() + ".html'>" + s.getFullName() + "</a></li>");
            }
            out.printf("</ul>");
        }
    }

    /**
     *
     * @param f
     * @param i
     */
    public void printChildren(Family f, Individual i) {
        if (i.getID() == f.father.getID()) {
            out.printf("Spouse: <a href='" + f.mother.getID() + ".html'>" + f.mother.getFullName() + "</a><br>");
        } else {
            out.printf("Spouse: <a href='" + f.father.getID() + ".html'>" + f.father.getFullName() + "</a><br>");
        }
        if (f.children.values().size() > 0) {
            out.printf("Children:<ul>");
            for (Individual s : f.children.values()) {
                out.printf("<li><a href='"  + s.getID() + ".html'>"+ s.getFullName() + "</a></li>");
            }
            out.printf("</ul>");
        }
    }

    /**
     *
     * @param d
     */
    public void printCitations(Holder d) {
        for (Holder h : d.getList(TagType.CITATION)) {
            if (!indexes.containsKey(h.parseID())) {
                indexes.put(h.parseID(), index);
                citations.put(index, h);
                index++;
            }
            out.printf("<a class='citation' href='#" + indexes.get(h.parseID()) + "'>[" + indexes.get(h.parseID()) + "]</a>");
        }
        out.printf("</li>");
    }
}
