package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.AuthentiCmd;
import com.undeadscythes.authenticmd.service.Service;
import com.undeadscythes.gedform.exception.ParsingException;
import com.undeadscythes.genebase.GeneBase;
import com.undeadscythes.genebase.gedcom.GEDCOM;
import com.undeadscythes.supergenes.SuperGenes;
import com.undeadscythes.tipscript.TipScript;
import java.io.UnsupportedEncodingException;

/**
 * Load a {@link GEDCOM} from file and parse it into a {@link GeneBase}.
 *
 * @author UndeadScythes
 */
public class Load extends Service {
    private GeneBase geneBase;

    /**
     * {@inheritDoc}
     */
    public boolean run(final AuthentiCmd cmdHandler, final String[] args) {
        final SuperGenes program = (SuperGenes)cmdHandler;
        final TipScript out = program.getTipScript();
        if (args.length < 1) {
            out.println("No file specified.");
            return true;
        }
        final String fileName = args[0].endsWith(".ged") ? args[0] : args[0] + ".ged";
        try {
            if (args.length == 2) {
                geneBase = new GeneBase(new GEDCOM(fileName, args[1]));
            } else {
                geneBase = new GeneBase(new GEDCOM(fileName));
            }
            program.addGeneBase(geneBase);
            out.println("Loaded GEDCOM " + geneBase.getUID() + " from " + fileName + ".");
        } catch (ParsingException ex) {
            out.println("Parsing error: " + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            out.println("Cannot use specified encoding.");
        }
        return true;
    }

    /**
     * Get the {@link GeneBase} that was loaded by this {@link Service}.
     */
    public GeneBase getGeneBase() {
        return geneBase;
    }
}
