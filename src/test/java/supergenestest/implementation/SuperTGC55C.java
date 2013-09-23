package supergenestest.implementation;

import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.service.*;
import com.undeadscythes.tipscript.*;

/**
 * @author UndeadScythes
 */
public final class SuperTGC55C extends SuperGenes {
    public static final TipRedirect REDIRECT = new TipRedirect();

    public SuperTGC55C() {
        super(System.in, REDIRECT);
        new Load().run(this, new String[]{"src/test/resources/TGC55C", "US-ASCII"});
    }
}
