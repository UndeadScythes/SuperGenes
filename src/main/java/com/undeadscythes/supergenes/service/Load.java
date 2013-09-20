package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.gedform.exception.*;
import com.undeadscythes.genebase.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.tipscript.*;

/**
 * @author UndeadScythes
 */
public class Load implements Service {
    public boolean run(final AuthentiCmd cmdHandler, final String[] args) {
        final SuperGenes program = (SuperGenes)cmdHandler;
        final TipScript out = program.getTipScript();
        if (args.length < 1) {
            out.println("No file specified.");
            return true;
        }
        try {
            final GeneBase geneBase = new GeneBase(new GEDCOM(args[0] + ".ged"));
            program.addGeneBase(geneBase.getUID().toString(), geneBase);
            out.println("Loaded GEDCOM " + geneBase.getUID() + " from " + args[0] + ".ged.");
        } catch (ParsingException ex) {
            out.println("Cannot parse this file.");
        }
        return true;
    }
}
