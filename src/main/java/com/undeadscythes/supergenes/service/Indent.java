package com.undeadscythes.supergenes.service;

import com.undeadscythes.gedform.*;
import java.io.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Indent extends AncestryService {
    @Override
    public boolean run(String[] args) {
        if (!out.openFile(gedcom.getName() + ".uged")) {
            out.println("Cannot open output file.");
            return true;
        }
        for (Record record : gedcom.getMaster()) {
            for (Iterator<LineStruct> i = record.iterator(); i.hasNext();) {
                LineStruct line = i.next();
                String indent = "";
                for (int j = 0; j < line.level; j++) {
                    indent += "  ";
                }
                out.printf(indent + line.tag.getTag() + " " + line.value);
            }
        }
        out.closeFile();
        out.println("Indented GEDCOM saved to " + gedcom.getName() + ".uged.");
        return true;
    }
}
