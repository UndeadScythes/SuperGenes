package supergenestest;

import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.service.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;
import org.yaml.snakeyaml.*;
import supergenestest.implementation.*;

/**
 * @author UndeadScythes
 */
public class ServiceTest {
    @Test
    public void testLoadFile() {
        final SuperGenes program = new SuperGenes();
        new Load().run(program, new String[]{"src/test/resources/test"});
        assertEquals("load", 7, program.getGeneBase("test").getGEDCOM().size());
    }

    @Test
    public void testValidYAML() throws FileNotFoundException, IOException {
        new Yamlize().run(new SuperGenesImpl(), new String[]{"test"});
        final Yaml yaml = new Yaml();
        final File file = new File("test.yml");
        assertTrue("create", file.exists());
        final FileInputStream input = new FileInputStream(file);
        yaml.load(input);
        input.close();
        assertTrue("delete", new File("test.yml").delete());
    }

    @Test
    public void timeline() {
        new Timeline().run(new SuperGenesImpl(), new String[]{"test", "I1"});
    }
}
