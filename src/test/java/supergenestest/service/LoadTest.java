package supergenestest.service;

import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.service.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class LoadTest extends Assert {
    /**
     *
     */
    @Test
    public void testLoadFile() {
        final SuperGenes program = new SuperGenes();
        new Load().run(program, new String[]{"src/test/resources/test"});
        assertEquals("load", 6, program.getGeneBase("test").getGEDCOM().size());
    }
}
