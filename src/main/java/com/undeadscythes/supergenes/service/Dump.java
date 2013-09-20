package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.record.*;

/**
 * @author UndeadScythes
 */
public class Dump extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        final Individual indi = getIndividual(args);
        if (indi.getUID().isNull()) {
            if (!out.openFile(geneBase.getUID() + ".bug")) {
                out.println("Cannot open file.");
                return true;
            }
            geneBase.dump(out);
            out.closeFile();
            out.println("Output bug dump to " + geneBase.getUID() + ".bug.");
            return true;
        }
        indi.dump(out, "- ");
        return true;
    }
}
