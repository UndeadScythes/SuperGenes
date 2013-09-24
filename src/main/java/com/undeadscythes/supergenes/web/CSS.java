package com.undeadscythes.supergenes.web;

import com.undeadscythes.genebase.*;
import java.io.*;
import org.apache.commons.io.*;

/**
 * Handles CSS generation.
 *
 * @author UndeadScythes
 */
public final class CSS extends PageBuilder {
    public boolean publish(final String rootDir, final GeneBase geneBase) {
        try {
            FileUtils.copyURLToFile(CSS.class.getResource("/web/supergenes.css"), new File(rootDir + "/supergenes.css"));
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
}
