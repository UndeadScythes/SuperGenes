package supergenestest;

import com.undeadscythes.genebase.gedcom.*;
import com.undeadscythes.supergenes.exception.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class ExceptionTest {
    @Test
    public void testGEDCOMLoadException() {
        try {
            throw new GEDCOMLoadException("test", 3, new NullPointerException());
        } catch (GEDCOMLoadException ex) {
            assertEquals("GEDCOMLoad", "Could not parse 'test' on line 3.", ex.getMessage());
        }
    }

    @Test
    public void testNoMemberFoundException() {
        try {
            throw new NoMemberFoundException(3);
        } catch (NoMemberFoundException ex) {
            assertEquals("NoMemberFound", "No member with unique ID 3 exists.", ex.getMessage());
        }
    }

    @Test
    public void testTagNotSetException() {
        try {
            throw new TagNotSetException(GEDTag.ABBR);
        } catch (TagNotSetException ex) {
            assertEquals("TagNotSet", "No value set for tag ABBR.", ex.getMessage());
        }
    }
}
