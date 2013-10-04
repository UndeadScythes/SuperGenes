package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.AuthentiCmd;
import com.undeadscythes.authenticmd.service.Service;
import com.undeadscythes.genebase.GeneBase;
import com.undeadscythes.supergenes.SuperGenes;
import com.undeadscythes.tipscript.TipScript;

/**
 * Select a particular {@link GeneBase} to use by default in all future
 * {@link Service services}.
 *
 * @author UndeadScythes
 */
public class Auto extends Service {
    @Override
    public boolean run(final AuthentiCmd cmdHandler, final String[] args) {
        final SuperGenes program = (SuperGenes)cmdHandler;
        final TipScript out = program.getTipScript();
        if (args.length < 1) {
            out.println("No GEDCOM specified.");
            return true;
        }
        final GeneBase genebase = program.getGeneBase(args[0]);
        if (genebase.isNull()) {
            out.println("Connot find requested GEDCOM.");
            return true;
        }
        program.setDefault(genebase);
        out.println("Default GEDCOM set to " + genebase.getUID() + ".");
        return true;
    }
}
