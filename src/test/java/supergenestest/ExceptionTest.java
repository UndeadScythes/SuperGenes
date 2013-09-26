package supergenestest;

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
            assertEquals("Could not parse 'test' on line 3.", ex.getMessage());
        }
    }

    @Test
    public void testNoMemberFoundException() {
        try {
            throw new NoMemberFoundException(3);
        } catch (NoMemberFoundException ex) {
            assertEquals("No member with unique ID 3 exists.", ex.getMessage());
        }
    }
}
