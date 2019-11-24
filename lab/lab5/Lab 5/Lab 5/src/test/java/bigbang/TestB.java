package bigbang;

import TestUtil.TestUtil;
import modules.ModuleB;
import modules.ModuleF;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import data.Entry;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

// IO Exception catching is not tested
// Explain in the report that it's just calling a library function
// Also, the scenario was never encountered e.g. reading /etc/shadow

public class TestB {

    ModuleB mb;
    ModuleF mf;
    final static String TEST_FILENAME = "BTEST_FILE";
    static File f;

    @BeforeEach
    public void setUp() throws IOException {
        mf = mock(ModuleF.class);
        mb = new ModuleB(mf);

        f = new File(TEST_FILENAME);
        f.createNewFile();
        Files.writeString(Paths.get(TEST_FILENAME), """
This
is,some
test
data""");
    }

    @AfterEach
    public void tearDown(){
        f.delete();
    }

    @Test
    public void loadFileTestValidFile() {
       ArrayList<Entry> ret =  mb.loadFile(TEST_FILENAME);

       ArrayList<Entry> expected = new ArrayList<>() {{
           add(new Entry("is", "some"));
       }};

       TestUtil.compareArrayOfEntries(expected, ret);
    }

    @Test
    public void loadFileTestInValidFile()  {
       mb.loadFile("/");
    }

    @Test
    public void loadFileTestFileNotFound(){
        mb.loadFile("");
    }

    @Test
    public void setFTest(){
        ModuleF newF = mock(ModuleF.class);
        mb.setF(newF);
    }
}
