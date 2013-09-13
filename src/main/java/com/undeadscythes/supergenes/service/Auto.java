package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.gedcom.*;
import com.undeadscythes.tipscript.*;

/**
 * @author UndeadScythes
 */
public class Auto implements Service {
    public boolean run(final AuthentiCmd cmdHandler, final String[] args) {
        SuperGenes program = (SuperGenes)cmdHandler;
        TipScript out = program.getTipScript();
        if (args.length < 1) {
            out.println("No GEDCOM specified.");
            return true;
        }
        GEDCOM gedcom = program.getGEDCOM(args[0]);
        if (gedcom == null) {
            out.println("Connot find requested GEDCOM.");
            return true;
        }
        program.setAuto(gedcom);
        out.println("Default GEDCOM set to " + gedcom.getName() + ".");
        return true;
    }
}
