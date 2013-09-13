package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.gedcom.*;
import com.undeadscythes.tipscript.*;
import java.io.*;

/**
 * @author UndeadScythes
 */
public class Load implements Service {
    public boolean run(AuthentiCmd cmdHandler, String[] args) {
        SuperGenes program = (SuperGenes)cmdHandler;
        TipScript out = program.getTipScript();
        if (args.length < 1) {
            out.println("No file specified.");
            return true;
        }
        try {
            File file = new File(args[0] + ".ged");
            GEDCOM gedcom = new GEDCOM();
            gedcom.load(file, out);
            program.addGEDCOM(file.getName(), gedcom);
            out.println("Loaded GEDCOM " + gedcom.getName() + " from " + args[0] + ".ged.");
        } catch (FileNotFoundException ex) {
            out.println("Cannot find this file.");
        } catch (IOException ex) {
            out.println("Cannot read this file.");
        }
        return true;
    }
}
