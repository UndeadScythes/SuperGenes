package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.specific.*;
import com.undeadscythes.supergenes.*;

/**
 * @author UndeadScythes
 */
public class NewTag implements Service {
    public boolean run(final AuthentiCmd program, final String[] args) {
        GEDTag.addTag(new CustomTag(args[0]));
        ((SuperGenes)program).getTipScript().println("Added " + args[0] + " as a custom tag.");
        return true;
    }
}
