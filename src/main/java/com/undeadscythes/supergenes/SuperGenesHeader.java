package com.undeadscythes.supergenes;

import com.undeadscythes.gedform.*;
import com.undeadscythes.gedform.exception.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.record.*;
import java.text.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class SuperGenesHeader extends Header {
    private static final long serialVersionUID = 1L;

    private static Cluster getRecord(final GEDCOM gedcom) {
        final Cluster record = new Cluster(9);
        try {
            record.add(new LineStruct("0 HEAD"));
            record.add(new LineStruct("1 SOUR"));
            record.add(new LineStruct("2 VERS " + SuperGenesHeader.class.getPackage().getImplementationVersion()));
            record.add(new LineStruct("2 NAME SuperGenes"));
            record.add(new LineStruct("2 CORP UndeadScythes' Empire"));
            record.add(new LineStruct("3 ADDR"));
            record.add(new LineStruct("4 CTRY England"));
            record.add(new LineStruct("3 EMAIL daveyognaut2@@gmail.com"));
            record.add(new LineStruct("3 WWW http://undeadscythes.com"));
            try {
                record.add(new LineStruct("2 DATA " + gedcom.pullCluster().getValue("SOUR")));
                record.add(new LineStruct("1 DATE " + new SimpleDateFormat("dd MM yyyy").format(new Date()).toUpperCase()));
            } catch (NoTagSetException ex) {}
            record.add(new LineStruct("2 TIME " + new SimpleDateFormat("HH:mm").format(new Date())));
            record.add(new LineStruct("1 FILE " + gedcom.getFileName()));
            record.add(new LineStruct("1 COPR Copyright " + new SimpleDateFormat("yyyy ").format(new Date()) + System.getProperties().getProperty("user.name")));
            record.add(new LineStruct("1 GEDC"));
            record.add(new LineStruct("2 VERS 5.5.1"));
            record.add(new LineStruct("2 FORM LINEAGE-LINKED"));
            record.add(new LineStruct("1 CHAR" + System.getProperties().getProperty("file.encoding")));
        } catch (ParsingException ex) {}
        return record;
    }

    /**
     *
     * @param gedcom
     */
    public SuperGenesHeader(final GEDCOM gedcom) {
        super(getRecord(gedcom));
    }
}
