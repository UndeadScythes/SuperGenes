package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.genebase.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.tipscript.*;

/**
 * @author UndeadScythes
 */
public class Auto implements Service {
    public boolean run(final AuthentiCmd cmdHandler, final String[] args) {
        final SuperGenes program = (SuperGenes)cmdHandler;
        final TipScript out = program.getTipScript();
        if (args.length < 1) {
            out.println("No GEDCOM specified.");
            return true;
        }
        final GeneBase genebase = program.getGeneBase(args[0]);
        if (genebase == null) {
            out.println("Connot find requested GEDCOM.");
            return true;
        }
        program.setAuto(genebase);
        out.println("Default GEDCOM set to " + genebase.getUID() + ".");
        return true;
    }
}
