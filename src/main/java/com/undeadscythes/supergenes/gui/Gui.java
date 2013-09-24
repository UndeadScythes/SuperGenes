package com.undeadscythes.supergenes.gui;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.tipscript.*;

/**
 * This service provides the user with a GUI.
 *
 * @author UndeadScythes
 */
public class Gui extends Service {
    @Override
    public boolean run(final AuthentiCmd program, final String[] args) {
        final TipRedirect output = new TipRedirect();
        SuperGenesGUI.main(((SuperGenes)program).clone(output), output);
        program.output.println("Command line operation will now close.");
        return false;
    }
}
