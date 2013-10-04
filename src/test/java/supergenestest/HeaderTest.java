package supergenestest;

import com.undeadscythes.genebase.record.Header;
import com.undeadscythes.metaturtle.exception.NoMetadataSetException;
import com.undeadscythes.supergenes.SuperGenesHeader;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author UndeadScythes
 */
public class HeaderTest {
    @Ignore
    @Test
    public void testCustomHeader() throws NoMetadataSetException {
        assertEquals("SuperGenes", SuperGenesHeader.getStandard().getFirst("SOUR.NAME").getValue());
        final Header header = new SuperGenesHeader("GEDitCOM", "");
        assertEquals("GEDitCOM", header.getFirst("SOUR.DATA").getValue());
    }
}
