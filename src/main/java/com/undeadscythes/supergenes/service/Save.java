package com.undeadscythes.supergenes.service;

import com.undeadscythes.genebase.record.Header;
import com.undeadscythes.metaturtle.exception.NoMetadataSetException;
import com.undeadscythes.supergenes.SuperGenesHeader;

/**
 * Save the given {@link com.undeadscythes.genebase.GeneBase} to file as a
 * {@link com.undeadscythes.genebase.gedcom.GEDCOM}.
 *
 * @author UndeadScythes
 */
public class Save extends AncestryService {
    @Override
    protected boolean run(final String[] args) {
        String output = geneBase.getUID().toString();
        if (args.length > 0) {
            output = args[0];
        }
        if (!output.endsWith(".ged")) output += ".ged";
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
        return true;
    }
}
