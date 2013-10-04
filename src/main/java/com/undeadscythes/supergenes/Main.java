package com.undeadscythes.supergenes;

import com.undeadscythes.authenticmd.exception.TerminationRequest;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Runtime class for {@link SuperGenes}.
 *
 * @author UndeadScythes
 */
public final class Main {
    private static final List<String> SERVICES;

    static {
        final String[] services = new String[]{
            "Age","Auto", "Dump", "Find", "FixMarr", "Gui", "Indent", "Load",
            "NewTag", "Relations", "ResToCen", "Save", "Timeline", "Web",
            "Yamlize"};
        SERVICES = new ArrayList<String>(services.length);
        for (String service : services) {
            SERVICES.add("com.undeadscythes.supergenes.service." + service);
        }
    }
    /**
     * Pass command line arguments.
     */
    @SuppressWarnings("empty-statement")
    public static void main(final String[] args) {
        final SuperGenes superGenes = new SuperGenes();
        superGenes.setServices(SERVICES, true);
        if (args.length == 0) {
            try {
                while (superGenes.getCommand("", 3));
            } catch (TerminationRequest ex) {}
        } else {
            try {
                superGenes.executeCmds("Executing commandline...", StringUtils.join(args, " ").split("-"));
            } catch (TerminationRequest ex) {}
        }
        superGenes.getOutput().println("Closing...");
    }

    private Main() {}
}
