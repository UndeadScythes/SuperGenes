package com.undeadscythes.supergenes.service;

import com.undeadscythes.gedform.*;
import java.util.*;

/**
 * Save a copy of the given {@link com.undeadscythes.genebase.gedcom.GEDCOM} to
 * file with indentations to denote the record hierarchy.
 *
 * @author UndeadScythes
 */
public class Indent extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        if (!out.openFile(geneBase.getUID() + ".uged")) {
            out.println("Cannot open output file.");
            return true;
        }
        for (Cluster record : geneBase.getGEDCOM()) {
            for (final Iterator<LineStruct> i = record.iterator(); i.hasNext();) {
                final LineStruct line = i.next();
                String indent = "";
                for (int j = 0; j < line.level; j++) {
                    indent += "  ";
                }
                out.printf(indent + line.tag + " " + line.value);
            }
        }
        out.closeFile();
        out.println("Indented GEDCOM saved to " + geneBase.getUID() + ".uged.");
        return true;
    }
}
