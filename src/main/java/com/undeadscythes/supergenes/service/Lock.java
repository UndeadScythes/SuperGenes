package com.undeadscythes.supergenes.service;

import com.undeadscythes.supergenes.individual.*;

/**
 * @author UndeadScythes
 */
public class Lock extends AncestryService {
    @Override
    public boolean run(String[] args) {
        Individual i = getIndividual(args);
        if (i.getID() > 0) {
            program.setLock(i);
            program.output.println("Locked on individual " + i.getFullName() + " ("+ i.getID() + ").");
        }
        return true;
    }
}
