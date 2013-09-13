package com.undeadscythes.supergenes.service;

import com.undeadscythes.supergenes.individual.*;

/**
 * @author UndeadScythes
 */
public class Dump extends AncestryService {
    @Override
    public boolean run(String[] args) {
        Individual i = getIndividual(args);
        if (i.getID() == 0) {
            if (!out.openFile(gedcom.getName() + ".bug")) {
                out.println("Cannot open file.");
                return true;
            }
            gedcom.dump(out);
            out.closeFile();
            out.println("Output bug dump to " + gedcom.getName() + ".bug.");
        }
        i.dump(out, "- ");
        return true;
    }
}
