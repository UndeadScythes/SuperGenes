package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.supergenes.*;
import static com.undeadscythes.supergenes.gedcom.MasterTag.*;

/**
 * @author UndeadScythes
 */
public class NewTag implements Service {
    public boolean run(AuthentiCmd program, String[] args) {
        addTag(args[0]);
        ((SuperGenes)program).getTipScript().println("Added " + args[0] + " as a custom tag.");
        return true;
    }
}
