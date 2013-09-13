package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.gedcom.*;
import com.undeadscythes.supergenes.individual.*;
import com.undeadscythes.tipscript.*;
import static java.lang.Integer.*;
import static java.util.Arrays.*;

/**
 * @author UndeadScythes
 */
public abstract class AncestryService implements Service {
    /**
     *
     */
    protected GEDCOM gedcom;
    /**
     *
     */
    protected TipScript out;
    /**
     *
     */
    protected SuperGenes program;

    public boolean run(AuthentiCmd cmdHandler, String[] args) {
        program = (SuperGenes)cmdHandler;
        gedcom = program.getAuto();
        out = program.getTipScript();
        String[] newArgs = args;
        if (gedcom == null) {
            if (args.length < 1) {
                out.println("No GEDCOM specified.");
                return true;
            }
            gedcom = program.getGEDCOM(args[0]);
            if (gedcom == null) {
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
     * @return
     */
    protected Individual getIndividual(final String[] args) {
        Individual i = null;
        if (args.length > 0) {
            if (args[0].matches("[0-9]+")) {
                i = (Individual)gedcom.get(GEDCOMTag.INDIVIDUAL, parseInt(args[0]));
            }
        } else {
            i = program.getLock();
        }
        if (i == null) {
            return Nobody.NOBODY;
        }
        return i;
    }

    /**
     *
     * @param args
     * @return
     */
    public abstract boolean run(String[] args);
}
