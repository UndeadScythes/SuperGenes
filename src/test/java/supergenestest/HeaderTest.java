package supergenestest;

import com.undeadscythes.genebase.record.*;
import com.undeadscythes.metaturtle.exception.*;
import com.undeadscythes.supergenes.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class HeaderTest {
    @Test
    public void testCustomHeader() throws NoMetadataSetException {
        assertEquals("SuperGenes", SuperGenesHeader.getStandard().getFirst("SOUR.NAME").getValue());
        final Header header = new SuperGenesHeader("GEDitCOM", "");
        assertEquals("GEDitCOM", header.getFirst("SOUR.DATA").getValue());
    }
}
