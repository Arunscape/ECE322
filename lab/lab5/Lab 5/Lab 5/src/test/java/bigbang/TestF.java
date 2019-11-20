package bigbang;

import data.Entry;
import modules.ModuleF;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestF {


    @Test
    public void testModuleF(){
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();

        ModuleF mf = new ModuleF();
        mf.setOutputStream(new PrintStream(stdout));

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry("name1", "number1"));
        entries.add(new Entry("name2", "number2"));
        entries.add(new Entry("name3", "number3"));
        entries.add(new Entry("name4", "number4"));
        entries.add(new Entry("name5", "number5"));

        mf.displayData(entries);

        assertEquals("""
Current Data:
1 name1, number1
2 name2, number2
3 name3, number3
4 name4, number4
5 name5, number5
""", stdout.toString());
    }
}
