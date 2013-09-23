package com.undeadscythes.supergenes.service;

import com.undeadscythes.gedform.*;
import com.undeadscythes.gedform.exception.*;
import com.undeadscythes.genebase.gedcom.*;
import java.util.*;

/**
 * Save the requested {@link com.undeadscythes.genebase.GeneBase} into a YAML
 * version of its source {@link GEDCOM}.
 *
 * @author UndeadScythes
 */
public class Yamlize extends AncestryService {
    private static LineStruct blank;

    static {
        try {
            blank = new LineStruct("0 TRLR");
        } catch (ParsingException ex) {}
    }

    public boolean run(final String[] args) {
        if (!out.openFile(geneBase.getUID() + ".yml")) {
            out.println("Cannot read this file.");
        }
        final GEDCOM master = geneBase.getGEDCOM();
        for (Cluster record : master) {
            for (final ListIterator<LineStruct> i = record.listIterator(); i.hasNext();) {
                final LineStruct line = i.next();
                if (i.hasNext()) {
                    out.printf(buildLine(line, i.next()));
                    i.previous();
                } else {
                    out.printf(buildLine(line, blank));
                }
            }
        }
        out.closeFile();
        out.println("Yamlized GEDCOM saved to " + geneBase.getUID() + ".yml.");
        return true;
    }

    private String buildLine(final LineStruct line, final LineStruct next) {
        final StringBuilder string = new StringBuilder("");
        final StringBuilder prefix = new StringBuilder("");
        for (int i = 0; i < line.level; i++) {
            prefix.append("  ");
        }
        string.append(prefix).append("- ").append(line.tag).append(":");
        if (!line.value.isEmpty()) {
            if (next.level > line.level) {
                string.append("\n").append(prefix).append("  - Value: ").append(sanitize(line.value));
            } else {
                string.append(" ").append(sanitize(line.value));
            }
        }
        if (!line.xref.isEmpty()) {
            string.append("\n").append(prefix).append("  - XREF: ").append(line.xref);
        }
        return string.toString();
    }

    private String sanitize(final String string) {
        String value = string.replace("\n", " ## ").replaceAll(":", "#");
        if (!value.isEmpty() && !String.valueOf(value.charAt(0)).matches("[0-9a-zA-Z]")) {
            value = "#" + value;
        }
        return value;
    }
}
