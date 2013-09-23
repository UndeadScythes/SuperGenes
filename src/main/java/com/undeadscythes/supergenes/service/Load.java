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
public class Load implements Service {
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
        try {
            final GeneBase geneBase;
            if (args.length == 2) {
                geneBase = new GeneBase(new GEDCOM(args[0] + ".ged", args[1]));
            } else {
                geneBase = new GeneBase(new GEDCOM(args[0] + ".ged"));
            }
            program.addGeneBase(geneBase.getUID().toString(), geneBase);
            out.println("Loaded GEDCOM " + geneBase.getUID() + " from " + args[0] + ".ged.");
        } catch (ParsingException ex) {
            out.println("Cannot parse this file.");
        } catch (UnsupportedEncodingException ex) {
            out.println("Cannot use specified encoding.");
        }
        return true;
    }
}
