package com.undeadscythes.supergenes.web;

import com.undeadscythes.genebase.*;
import com.undeadscythes.hyperform.*;
import com.undeadscythes.metaturtle.exception.*;
import com.undeadscythes.metaturtle.metadata.*;
import java.io.*;

/**
 * Controls generation of the website homepage.
 *
 * @author UndeadScythes
 */
public final class Index extends PageBuilder {
    private static GeneBase geneBase;

    public boolean publish(final String rootDir, final GeneBase geneBase) {
        Index.geneBase = geneBase;
        final HyperForm form = new HyperForm();
        resetCitations();
        form.load("title", " Home");
        form.load("prefix", "");
        form.load("h1", geneBase.getUID().toString());
        form.load("h2", "Home");
        form.load("gedcom", getHeader());
        form.load("main", new HyperOp(HyperType.PARSE, "/web/index.xml"));
        form.publish(form.parse(Index.class.getResourceAsStream("/web/layout.xml")), new File(rootDir + "/index.html"));
        return true;
    }

    private String getHeader() {
        final StringBuilder output = new StringBuilder("");
        try {
            for (Metadata fact : geneBase.getFirst("HEAD")) {
                output.append(getData(fact));
            }
        } catch (NoMetadataSetException ex) {
            output.append("No header data available.");
        }
        return output.toString();
    }
}
