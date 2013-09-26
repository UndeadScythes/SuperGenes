package com.undeadscythes.supergenes.web;

import com.undeadscythes.genebase.*;
import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.genebase.holder.*;
import com.undeadscythes.metaturtle.exception.*;
import com.undeadscythes.metaturtle.metadata.*;
import java.util.*;

/**
 * This class provides basic tools for generating web pages.
 *
 * @author UndeadScythes
 */
public abstract class PageBuilder {
    private Map<Integer, Holder> citations;
    private Map<String, Integer> indexes;
    private int index;

    /**
     * Get a {@link String} containing the data in a given {@link Metadata}.
     */
    protected String getData(final Metadata meta) {
        final StringBuilder output = new StringBuilder("");
        final String formal = GEDTag.getByName(meta.getProperty()).getFormal();
        output.append("<li>").append(formal).append(": ");
        if (meta.getValue().toLowerCase().startsWith("http")) {
            output.append("<a target='_blank' href='").append(meta.getValue()).append("'>").append(meta.getValue()).append("</a>");
        } else {
            output.append(meta.getValue());
        }
        if (!meta.isEmpty()) {
            output.append("<ul>");
            for (Metadata fact : meta) {
                output.append(getData(fact));
            }
            output.append("</ul>");
        }
        output.append(getCitations(meta));
        return output.toString();
    }

    /**
     * Reset the current citations list and index.
     */
    protected void resetCitations() {
        citations = new HashMap<Integer, Holder>(0);
        indexes = new HashMap<String, Integer>(0);
        index = 1;
    }

    /**
     * Get a {@link String} containing the citations of a given
     * {@link Metadata}.
     */
    protected String getCitations(final Metadata meta) {
        final StringBuilder output = new StringBuilder("");
        for (Metadata citation : meta.getList(GEDTag.SOUR)) {
            if (!indexes.containsKey(citation.getValue())) {
                indexes.put(citation.getValue(), index);
                citations.put(index, (Holder)citation);
                index++;
            }
            output.append("<a class='citation' href='#" + indexes.get(citation.getValue()) + "'>[" + indexes.get(citation.getValue()) + "]</a>");
        }
        output.append("</li>");
        return output.toString();
    }

    /**
     * Generate the given page and store it in the given directory, return true
     * on success.
     */
    public abstract boolean publish(final String rootDir, final GeneBase geneBase);
}
