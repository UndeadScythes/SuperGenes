package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.genebase.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import com.undeadscythes.metaturtle.exception.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.tipscript.*;
import java.util.*;

/**
 * A wrapper of the basic {@link Service} class adding simple argument parsing
 * and convenience fields for child classes.
 *
 * @author UndeadScythes
 */
public abstract class AncestryService extends Service {
    /**
     * The {@link GeneBase} that the parent {@link SuperGenes program} wants
     * {@link Service services} to use by default.
     */
    protected GeneBase geneBase;
    /**
     * The {@link TipScript output} method the parent {@link SuperGenes program}
     * is using.
     */
    protected TipScript out;
    /**
     * The {@link SuperGenes program} that ran this {@link Service}.
     */
    protected SuperGenes program;

    /**
     * {@inheritDoc}
     */
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
            newArgs = Arrays.copyOfRange(args, 1, args.length);
        }
        return run(newArgs);
    }

    /**
     * Get the {@link Individual} indicated by the arguments.
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
     * Return false if this {@link Service service} is requesting the parent
     * {@link SuperGenes program} to quit.
     */
    public abstract boolean run(final String[] args);
}
