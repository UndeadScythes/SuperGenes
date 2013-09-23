package com.undeadscythes.supergenes.validator;

import com.undeadscythes.authenticmd.validator.*;
import com.undeadscythes.genebase.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import com.undeadscythes.metaturtle.exception.*;

/**
 * Validate a {@link String} by matching it with an {@link Individual}
 * {@link com.undeadscythes.metaturtle.unique.UID} in a particular
 * {@link GeneBase}.
 *
 * @author UndeadScythes
 */
public class IndividualValidator implements Validator {
    private final GeneBase genebase;
    private Individual indi;

    /**
     * Load a particular {@link GeneBase} to validate {@link Individual}
     * {@link com.undeadscythes.metaturtle.unique.UID UIDs}.
     */
    public IndividualValidator(final GeneBase genebase) {
        this.genebase = genebase;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValid(final String response) {
        try {
            indi = (Individual)genebase.getUniqueMeta(RecordType.INDI, response);
            return true;
        } catch (NoUniqueMetaException ex) {
            return false;
        }
    }

    /**
     * Get the validated {@link Individual}.
     */
    public Individual getValidIndi() {
        return indi;
    }
}
