package supergenestest.implementation;

import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.service.*;

/**
 * @author UndeadScythes
 */
public final class SuperGenesImpl extends SuperGenes {
    public SuperGenesImpl() {
        new Load().run(this, new String[]{"src/test/resources/test"});
    }
}
