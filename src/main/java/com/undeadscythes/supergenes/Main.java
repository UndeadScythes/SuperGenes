package com.undeadscythes.supergenes;

import com.undeadscythes.authenticmd.service.*;
import java.util.*;
import org.apache.commons.lang3.*;
import org.reflections.*;

/**
 * Runtime class for {@link SuperGenes}.
 *
 * @author UndeadScythes
 */
public final class Main {
    /**
     * Pass command line arguments.
     */
    public static void main(final String[] args) {
        final SuperGenes superGenes = new SuperGenes();
        final Set<Class<? extends Service>> classes = new Reflections(Main.class.getPackage().getName() + ".service").getSubTypesOf(Service.class);
        final List<String> services = new ArrayList<String>(0);
        for (Class<? extends Service> clazz : classes) {
            services.add(clazz.getName());
        }
        superGenes.setServices(services, true);
        if (args.length == 0 || superGenes.executeCmds("Executing commandline...", StringUtils.join(args, " ").split("-"))) {
            while (superGenes.getCommand("", 3));
        }
        superGenes.output.println("Closing...");
    }

    private Main() {}
}
