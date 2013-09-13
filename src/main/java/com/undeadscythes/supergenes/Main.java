package com.undeadscythes.supergenes;

import com.undeadscythes.authenticmd.service.*;
import java.util.*;
import static org.apache.commons.lang3.StringUtils.*;
import org.reflections.*;

/**
 * @author UndeadScythes
 */
public class Main {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SuperGenes program = new SuperGenes();
        Reflections ref = new Reflections("com.undeadscythes.udsancestry.service");
        Set<Class<? extends Service>> classes = ref.getSubTypesOf(Service.class);
        List<String> services = new ArrayList<String>(0);
        for (Class<? extends Service> clazz : classes) {
            services.add(clazz.getName());
        }
        program.setServices(services, true);
        boolean cont = true;
        if (args.length > 0) cont = program.executeCmds("Executing commandline...", join(args, " ").split("-"));
        if (cont) while (program.getCommand("", 3)) {}
        program.output.println("Closing...");
    }
}
