package com.undeadscythes.supergenes.service;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.supergenes.*;

/**
 * Add a {@link CustomTag custom tag} to the recognized list of
 * {@link GEDTag tags} used by the parser.
 *
 * @author UndeadScythes
 */
public class NewTag implements Service {
    /**
     * {@inheritDoc}
     */
    public boolean run(final AuthentiCmd program, final String[] args) {
        GEDTag.addTag(new CustomTag(args[0]));
        ((SuperGenes)program).getTipScript().println("Added " + args[0] + " as a custom tag.");
        return true;
    }
}
