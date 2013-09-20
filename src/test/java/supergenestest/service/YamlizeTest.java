package supergenestest.service;

import com.undeadscythes.supergenes.service.*;
import java.io.*;
import org.junit.*;
import org.yaml.snakeyaml.*;
import static supergenestest.TestSuperGenesImpl.*;

/**
 * @author UndeadScythes
 */
public class YamlizeTest extends Assert {
    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testValidYAML() throws FileNotFoundException, IOException {
        new Yamlize().run(newProject(), new String[]{"test"});
        final Yaml yaml = new Yaml();
        final File file = new File("test.yml");
        assertTrue("create", file.exists());
        FileInputStream in = new FileInputStream(file);
        yaml.load(in);
        in.close();
        assertTrue("delete", new File("test.yml").delete());
    }
}
