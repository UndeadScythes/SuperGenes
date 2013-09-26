package com.undeadscythes.supergenes;

import com.undeadscythes.gedform.*;
import com.undeadscythes.gedform.exception.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import java.text.*;
import java.util.*;

/**
 * A customized {@link Header} to be used when saving {@link GEDCOM GEDCOMs}.
 *
 * @author UndeadScythes
 */
public class SuperGenesHeader extends Header {
    private static final long serialVersionUID = 1L;

    private static Cluster getRecord(final String source, final String file) {
        final Cluster record = new Cluster(9);
        try {
            record.add(new LineStruct("0 HEAD"));
            record.add(new LineStruct("1 SOUR"));
            record.add(new LineStruct("2 VERS " + SuperGenesHeader.class.getPackage().getImplementationVersion()));
            record.add(new LineStruct("2 NAME SuperGenes"));
            record.add(new LineStruct("2 CORP UndeadScythes' Empire"));
            record.add(new LineStruct("3 ADDR England"));
            record.add(new LineStruct("4 CTRY England"));
            record.add(new LineStruct("3 EMAIL daveyognaut2@@gmail.com"));
            record.add(new LineStruct("3 WWW http://undeadscythes.com"));
            record.add(new LineStruct("2 DATA " + source));
            record.add(new LineStruct("1 DATE " + new SimpleDateFormat("dd MMM yyyy").format(new Date()).toUpperCase()));
            record.add(new LineStruct("2 TIME " + new SimpleDateFormat("HH:mm").format(new Date())));
            record.add(new LineStruct("1 FILE " + file));
            record.add(new LineStruct("1 COPR Copyright " + new SimpleDateFormat("yyyy ").format(new Date()) + System.getProperties().getProperty("user.name")));
            record.add(new LineStruct("1 GEDC"));
            record.add(new LineStruct("2 VERS 5.5.1"));
            record.add(new LineStruct("2 FORM LINEAGE-LINKED"));
            record.add(new LineStruct("1 CHAR " + System.getProperties().getProperty("file.encoding"))); //TODO: Fix encoding
        } catch (ParsingException ex) {}
        return record;
    }

    /**
     * Return a standard {@link Header} specific to {@link SuperGenes}.
     */
    public static SuperGenesHeader getStandard() {
        return new SuperGenesHeader("", "");
    }

    /**
     * Return a custom {@link Header} as a parent of the given {@link GEDCOM}.
     */
    public SuperGenesHeader(final String source, final String file) {
        super(getRecord(source, file));
    }
}
