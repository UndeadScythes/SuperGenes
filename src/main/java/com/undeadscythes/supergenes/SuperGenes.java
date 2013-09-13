package com.undeadscythes.supergenes;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.supergenes.gedcom.*;
import com.undeadscythes.supergenes.individual.*;
import com.undeadscythes.tipscript.*;
import java.util.*;
import static java.util.logging.Logger.*;

/**
 * @author UndeadScythes
 */
public class SuperGenes extends AuthentiCmd {
    private Map<String, GEDCOM> gedcoms = new HashMap<String, GEDCOM>(0);
    private GEDCOM auto = null;
    private Individual lock = null;

    /**
     *
     */
    public SuperGenes() {
        super(System.in, new TipScript(getLogger(SuperGenes.class.getName()), "UDSAncestry:> "));
    }

    /**
     *
     * @param name
     * @return
     */
    public GEDCOM getGEDCOM(final String name) {
        return gedcoms.get(name);
    }

    /**
     *
     * @param path
     * @param gedcom
     */
    public void addGEDCOM(final String path, final GEDCOM gedcom) {
        gedcoms.put(gedcom.getName(), gedcom);
    }

    /**
     *
     * @return
     */
    public GEDCOM getAuto() {
        return auto;
    }

    /**
     *
     * @param gedcom
     */
    public void setAuto(final GEDCOM gedcom) {
        auto = gedcom;
    }

    /**
     *
     * @return
     */
    public Individual getLock() {
        return lock;
    }

    /**
     *
     * @param i
     */
    public void setLock(final Individual i) {
        lock = i;
    }

    /**
     *
     * @return
     */
    public TipScript getTipScript() {
        return output;
    }
}
