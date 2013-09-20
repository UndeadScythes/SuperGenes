package com.undeadscythes.supergenes.service;

import com.undeadscythes.gedform.*;
import com.undeadscythes.genebase.gedcom.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Yamlize extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        if (!out.openFile(geneBase.getUID() + ".yml")) {
            out.println("Cannot read this file.");
        }
        final GEDCOM master = geneBase.getGEDCOM();
        for (Cluster record : master) {
            for (final ListIterator<LineStruct> i = record.listIterator(); i.hasNext();) {
                final LineStruct line = i.next();
                final StringBuilder builder = new StringBuilder("");
                for (int j = 0; j < line.level; j++) {
                   builder.append("  ");
                }
                final String prefix = builder.toString();
                if (!line.value.isEmpty()) {
                    final String value = line.value.replaceFirst("@", "xref-@");
                    if (i.hasNext()) {
                        if (i.next().level > line.level) {
                            out.printf(prefix + "- " + line.tag + ":");
                            out.printf(prefix + "  - Value: " + value);
                        } else {
                            out.printf(prefix + "- " + line.tag + ": " + value);
                        }
                        i.previous();
                    }
                } else {
                    out.printf(prefix + "- " + line.tag + ":");
                }
            }
        }
        out.closeFile();
        out.println("Yamlized GEDCOM saved to " + geneBase.getUID() + ".yml.");
        return true;
    }
}
