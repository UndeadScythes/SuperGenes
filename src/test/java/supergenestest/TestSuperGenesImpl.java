package supergenestest;

import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.service.*;

/**
 * @author UndeadScythes
 */
public final class TestSuperGenesImpl {
    public static SuperGenes newProject() {
        final SuperGenes superGenes = new SuperGenes();
        new Load().run(superGenes, new String[]{"src/test/resources/test"});
        return superGenes;
    }
}
