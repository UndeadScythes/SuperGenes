package com.undeadscythes.supergenes.service;

import com.undeadscythes.gedform.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Yamlize extends AncestryService {
    @Override
    public boolean run(String[] args) {
        if (!out.openFile(gedcom.getName() + ".yml")) {
            out.println("Cannot read this file.");
        }
        Transmission master = gedcom.getMaster();
        for (Record record : master) {
            for (ListIterator<LineStruct> i = record.listIterator(); i.hasNext();) {
                LineStruct line = i.next();
                String prefix = "";
                for (int j = 0; j < line.level; j++) {
                    prefix += "  ";
                }
                if (!line.value.isEmpty()) {
                    String value = line.value.replaceFirst("@", "xref-@");
                    if (i.hasNext()) {
                        if (i.next().level > line.level) {
                            out.printf(prefix + "- " + line.tag.getFriendly() + ":");
                            out.printf(prefix + "  - Value: " + value);
                        } else {
                            out.printf(prefix + "- " + line.tag.getFriendly() + ": " + value);
                        }
                        i.previous();
                    }
                } else {
                    out.printf(prefix + "- " + line.tag.getFriendly() + ":");
                }
            }
        }
        out.closeFile();
        out.println("Yamlized GEDCOM saved to " + gedcom.getName() + ".yml.");
        return true;
    }
}
