package com.undeadscythes.supergenes.service;

/**
 * @author UndeadScythes
 */
public class Parse extends AncestryService {
    @Override
    public boolean run(String[] args) {
        gedcom.parse(out);
        out.println("Parsed GEDCOM " + gedcom.getName() + " succesfully.");
        return true;
    }
}
