package com.undeadscythes.supergenes.gui;

import java.io.*;
import javax.swing.filechooser.FileFilter;

/**
 * A {@link FileFilter} to accept only files ending in '.ged'.
 *
 * @author UndeadScythes
 */
public class GEDFilter extends FileFilter {
    @Override
    public boolean accept(final File file) {
        return file.getName().endsWith(".ged") || file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "GEDCOM files (.ged)";
    }
}
