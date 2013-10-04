package com.undeadscythes.supergenes;

import com.undeadscythes.authenticmd.AuthentiCmd;
import com.undeadscythes.genebase.GeneBase;
import com.undeadscythes.metaturtle.unique.UID;
import com.undeadscythes.tipscript.TipScript;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

/**
 * The main beef of {@link SuperGenes} is the map that contains a collection of
 * {@link GeneBase}s that can be manipulated using the various
 * {@link com.undeadscythes.authenticmd.service.Service}s.
 *
 * @author UndeadScythes
 */
public class SuperGenes extends AuthentiCmd {
    private Map<String, GeneBase> geneBases = new HashMap<String, GeneBase>(0);
    private GeneBase defaultGeneBase = null;

    /**
     * Constructs a new default {@link SuperGenes} program with input
     * {@link System#in} and a {@link TipScript} using a named logger and prompt
     * "SuperGenes:> ".
     *
     * @see AuthentiCmd#AuthentiCmd(InputStream, TipScript)
     */
    public SuperGenes() {
        super(System.in, new TipScript(Logger.getLogger(SuperGenes.class.getName()), "SuperGenes:> "));
    }

    /**
     * Allows a superclass to specify its own input and outputs.
     *
     * @see AuthentiCmd#AuthentiCmd(InputStream, TipScript)
     */
    protected SuperGenes(final InputStream input, final TipScript output) {
        super(input, output);
    }

    /**
     * Get a {@link GeneBase} with the specified name.
     */
    public GeneBase getGeneBase(final String name) {
        if (!geneBases.containsKey(name)) return GeneBase.NULL_GENEBASE;
        return geneBases.get(name);
    }

    /**
     * Add a new {@link GeneBase}.
     */
    public void addGeneBase(final GeneBase geneBase) {
        addGeneBase(geneBase.getUID().toString(), geneBase);
    }

    /**
     * Add a {@link GeneBase} with a given name.
     */
    public void addGeneBase(final String name, final GeneBase geneBase) {
        geneBases.put(name, geneBase);
    }

    /**
     * Remove the GeneBase with a given UID.
     */
    public void removeGeneBase(final UID uid) {
        final Iterator<Map.Entry<String, GeneBase>> i = geneBases.entrySet().iterator();
        while (i.hasNext()) {
            if (i.next().getValue().getUID().equals(uid)) {
                i.remove();
                break;
            }
        }
    }

    /**
     * Get the default {@link GeneBase} that will be used with all
     * {@link com.undeadscythes.authenticmd.service.Service}s executed.
     */
    public GeneBase getDefault() {
        return defaultGeneBase;
    }

    /**
     * Set the {@link GeneBase} which will be used by default for each
     * {@link com.undeadscythes.authenticmd.service.Service} executed.
     */
    public void setDefault(final GeneBase geneBase) {
        defaultGeneBase = geneBase;
    }

    /**
     * Get the {@link TipScript} used by this program to handle output.
     */
    public TipScript getTipScript() {
        return getOutput();
    }

    /**
     * Get a collection of all stored {@link GeneBase GeneBases}.
     */
    public Collection<GeneBase> getGeneBases() {
        return geneBases.values();
    }

    /**
     * Create a clone of this {@link SuperGenes program} with a different
     * {@link TipScript output}.
     */
    public SuperGenes clone(final TipScript output) {
        final SuperGenes clone = new SuperGenes(System.in, output);
        clone.setDefault(defaultGeneBase);
        clone.setServices(getServices());
        for (GeneBase geneBase : geneBases.values()) {
            clone.addGeneBase(geneBase);
        }
        return clone;
    }
}
