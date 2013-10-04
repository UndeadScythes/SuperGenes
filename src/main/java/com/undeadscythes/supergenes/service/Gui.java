package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.AuthentiCmd;
import com.undeadscythes.authenticmd.service.Service;
import com.undeadscythes.supergenes.SuperGenes;
import com.undeadscythes.supergenes.gui.SuperGenesGUI;
import com.undeadscythes.tipscript.TipRedirect;

/**
 * This service provides the user with a GUI.
 *
 * @author UndeadScythes
 */
public class Gui extends Service {
    @Override
    public boolean run(final AuthentiCmd program, final String[] args) {
        final TipRedirect output = new TipRedirect();
        new SuperGenesGUI(((SuperGenes)program).clone(output), output);
        program.getOutput().println("Command line operation will now close.");
        return false;
    }
}
