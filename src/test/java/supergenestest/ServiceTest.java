package supergenestest;

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
    public void testLoad() {
        final SuperTGC55C program = new SuperTGC55C();
        assertEquals("load", 67, program.getGeneBase("TGC55C").getGEDCOM().size());
    }

    @Test
    public void testYamlize() throws FileNotFoundException, IOException {
        new Yamlize().run(new SuperTGC55C(), new String[]{"TGC55C"});
        final Yaml yaml = new Yaml();
        final File file = new File("TGC55C.yml");
        assertTrue("create", file.exists());
        final FileInputStream input = new FileInputStream(file);
        yaml.load(input);
        input.close();
        assertTrue("delete", new File("TGC55C.yml").delete());
    }

    @Test
    public void testTimeline() {
        new Timeline().run(new SuperTGC55C(), new String[]{"TGC55C", "PERSON4"});
    }

    @Test
    public void testAge() {
        new Age().run(new SuperTGC55C(), new String[]{"TGC55C", "PERSON4"});
    }

    @Test
    public void testAuto() {
        new Auto().run(new SuperTGC55C(), new String[]{"TGC55C", "TGC55C"});
    }

    @Test
    public void testDump() {
        new Dump().run(new SuperTGC55C(), new String[]{"TGC55C", ""});
        assertTrue("delete", new File("TGC55C.dump").delete());
    }

    @Test
    public void testFind() {
        new Find().run(new SuperTGC55C(), new String[]{"TGC55C", "Rick"});
    }

    @Test
    public void testFixMarr() {
        new FixMarr().run(new SuperTGC55C(), new String[]{"TGC55C", ""});
    }

    @Test
    public void testIndent() {
        new Indent().run(new SuperTGC55C(), new String[]{"TGC55C", ""});
        assertTrue("delete", new File("TGC55C.uged").delete());
    }

    @Test
    public void testNewTag() {
        new NewTag().run(new SuperTGC55C(), new String[]{"TGC55C", "_TEST"});
    }

    @Test
    public void testRelations() {
        new Relations().run(new SuperTGC55C(), new String[]{"TGC55C", "PERSON4"});
    }

    @Test
    public void testResToCen() {
        new ResToCen().run(new SuperTGC55C(), new String[]{"TGC55C", ""});
    }

    @Test
    public void testSave() {
        new Save().run(new SuperTGC55C(), new String[]{"TGC55C", "TGC55C"});
        assertTrue("delete", new File("TGC55C.ged").delete());
    }
}
