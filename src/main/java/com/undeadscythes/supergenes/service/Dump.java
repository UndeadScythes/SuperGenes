package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.record.*;

/**
 * Dump the contents of the given {@link com.undeadscythes.genebase.GeneBase}
 * to file along with various debugging information.
 *
 * @author UndeadScythes
 */
public class Dump extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        final Individual indi = getIndividual(args);
        if (indi.getUID().isNull()) {
            if (!out.openFile(geneBase.getUID() + ".dump")) {
                out.println("Cannot open file.");
                return true;
            }
            geneBase.dump(out);
            out.closeFile();
            out.println("Output bug dump to " + geneBase.getUID() + ".dump.");
            return true;
        }
        indi.dump(out, "- ");
        return true;
    }
}
