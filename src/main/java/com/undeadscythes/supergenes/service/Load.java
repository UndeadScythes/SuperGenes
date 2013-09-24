package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.gedform.exception.*;
import com.undeadscythes.genebase.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.tipscript.*;
import java.io.*;

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
            program.addGeneBase(geneBase.getUID().toString(), geneBase);
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
