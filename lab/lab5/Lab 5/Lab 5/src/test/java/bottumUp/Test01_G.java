package bottumUp;

import data.Entry;
import modules.ModuleG;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01_G {

    static String FILENAME = "GTEST_FILE";
    static File f;
    static ModuleG mg;

    @BeforeEach
    public void createFile(){
        f = new File(FILENAME);
        mg = new ModuleG();
    }

    @AfterEach
    public void deleteFile(){
        f.delete();
    }

    @Test
    public void testModuleG() throws IOException {

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry("name1", "number1"));
        entries.add(new Entry("name2", "number2"));
        entries.add(new Entry("name3", "number3"));
        entries.add(new Entry("name4", "number4"));
        entries.add(new Entry("name5", "number5"));

        mg.updateData(FILENAME, entries);

        // todo test output
        assertEquals("""
name1,number1
name2,number2
name3,number3
name4,number4
name5,number5
""", Files.readString(Paths.get(FILENAME)));
    }

    @Test
    public void testModuleGFail() {
        mg.updateData("", new ArrayList<Entry>());
    }
}
