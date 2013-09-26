package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import com.undeadscythes.metaturtle.exception.*;
import com.undeadscythes.supergenes.*;

/**
 * Save the given {@link com.undeadscythes.genebase.GeneBase} to file as a
 * {@link com.undeadscythes.genebase.gedcom.GEDCOM}.
 *
 * @author UndeadScythes
 */
public class Save extends AncestryService {
    @Override
    public boolean run(final String[] args) {
        String output = geneBase.getUID() + "-new.ged";
        if (args.length > 0) {
            output = args[0] + ".ged";
        }
        String source = "";
        Header header = null;
        try {
            header = (Header)geneBase.remove("HEAD");
            source = header.getFirst("SOUR").getValue();
        } catch (NoMetadataSetException ex) {}
        geneBase.add(new SuperGenesHeader(source, geneBase.getGEDCOM().getFileName()));
        out.openFile(output);
        geneBase.save(out);
        out.closeFile();
        out.println("GeneBase saved to " + output + ".");
        try {
            geneBase.remove("HEAD");
        } catch (NoMetadataSetException ex) {}
        if (header == null) {
            geneBase.add(header);
        }
        return true;
    }
}
