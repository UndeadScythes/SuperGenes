package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.genebase.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import com.undeadscythes.metaturtle.exception.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.tipscript.*;
import static java.util.Arrays.*;

/**
 * @author UndeadScythes
 */
public abstract class AncestryService implements Service {
    protected GeneBase geneBase;
    protected TipScript out;
    protected SuperGenes program;

    public boolean run(final AuthentiCmd cmdHandler, final String[] args) {
        program = (SuperGenes)cmdHandler;
        geneBase = program.getDefault();
        out = program.getTipScript();
        String[] newArgs = args;
        if (geneBase == null) {
            if (args.length < 1) {
                out.println("No GEDCOM specified.");
                return true;
            }
            geneBase = program.getGeneBase(args[0]);
            if (geneBase == null) {
                out.println("Connot find requested GEDCOM.");
                return true;
            }
            newArgs = copyOfRange(args, 1, args.length);
        }
        return run(newArgs);
    }

    /**
     *
     * @param args
     * @return The {@link Individual} indicated by the arguments
     */
    protected Individual getIndividual(final String[] args) {
        if (args.length > 0) {
            try {
                return  (Individual)geneBase.getUniqueMeta(RecordType.INDI, args[0]);
            } catch (NoUniqueMetaException ex) {}
        }
        return Individual.UNKNOWN;

    }

    /**
     *
     * @param args
     * @return False if execution should escape the {@link AuthentiCmd} response loop
     */
    public abstract boolean run(final String[] args);
}
