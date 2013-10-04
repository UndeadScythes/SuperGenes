package com.undeadscythes.supergenes.service;

/**
 * Rename a GeneBase.
 *
 * @author UndeadScythes
 */
public class Rename extends AncestryService {
    @Override
    protected boolean run(final String[] args) {
        program.addGeneBase(args[0], geneBase);
        program.removeGeneBase(geneBase.getUID());
        return true;
    }
}
