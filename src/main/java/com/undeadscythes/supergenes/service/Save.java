package com.undeadscythes.supergenes.service;

/**
 * @author UndeadScythes
 */
public class Save extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        String output = geneBase.getUID() + "-new.ged";
        if (args.length > 0) {
            output = args[0] + ".ged";
        }
        out.openFile(output);
        geneBase.save(out);
        out.closeFile();
        return true;
    }
}
