package supergenestest.service;

import com.undeadscythes.supergenes.service.*;
import org.junit.*;
import static supergenestest.TestSuperGenesImpl.*;

/**
 * @author UndeadScythes
 */
public class TimelineTest extends Assert {
    /**
     *
     */
    @Test
    public void timeline() {
        new Timeline().run(newProject(), new String[]{"test", "I1"});
    }
}
