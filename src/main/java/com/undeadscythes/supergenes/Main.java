package com.undeadscythes.supergenes;

import java.util.*;
import org.apache.commons.lang3.*;

/**
 * Runtime class for {@link SuperGenes}.
 *
 * @author UndeadScythes
 */
public final class Main {
    private static final List<String> SERVICES;

    static {
        final String[] services = new String[]{"Age", "Auto", "Dump", "Find", "FixMarr", "Indent", "Load", "NewTag", "Relations", "ResToCen", "Save", "Timeline", "Yamlize"};
        SERVICES = new ArrayList<String>(services.length);
        for (String service : services) {
            SERVICES.add("com.undeadscythes.supergenes.service." + service);
        }
        SERVICES.add("com.undeadscythes.supergenes.web.Web");
        SERVICES.add("com.undeadscythes.supergenes.gui.Gui");
    }
    /**
     * Pass command line arguments.
     */
    public static void main(final String[] args) {
        final SuperGenes superGenes = new SuperGenes();
        superGenes.setServices(SERVICES, true);
        if (args.length == 0 || superGenes.executeCmds("Executing commandline...", StringUtils.join(args, " ").split("-"))) {
            while (superGenes.getCommand("", 3));
        }
        superGenes.output.println("Closing...");
    }

    private Main() {}
}
