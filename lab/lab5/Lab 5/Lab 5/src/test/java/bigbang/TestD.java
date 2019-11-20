package bigbang;

import TestUtil.TestUtil;
import data.Entry;
import modules.ModuleD;
import modules.ModuleF;
import modules.ModuleG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;


public class TestD {

    ModuleF mf;
    ModuleG mg;
    ModuleD md;

    final static String TEST_NAME = "testName";
    final static String TEST_NUMBER = "testNumber";
    final static String TEST_FILENAME = "testFilename";
    final static int TEST_INDEX = 5;

    ArrayList<Entry> expected;

    @BeforeEach
    public void setUp(){
        mf = mock(ModuleF.class);
        mg = mock(ModuleG.class);

        md = new ModuleD(mf, mg);

        expected = new ArrayList<>() {{
            for (int i = 0; i < 10; i += 1)
                add(new Entry(TEST_NAME + i, TEST_NUMBER + i));
        }};
    }

    @Test
    public void insertDataTest(){
        ArrayList<Entry> ret= md.insertData((ArrayList<Entry>)expected.clone(), TEST_NAME, TEST_NUMBER, TEST_FILENAME);

        expected.add(new Entry(TEST_NAME, TEST_NUMBER));

        TestUtil.compareArrayOfEntries(expected, ret);
    }

    @Test
    public void updateDataTest(){
        ArrayList<Entry> ret = md.updateData((ArrayList<Entry>) expected.clone(), TEST_INDEX, TEST_NAME, TEST_NUMBER, TEST_FILENAME);

        expected.set(TEST_INDEX, new Entry(TEST_NAME, TEST_NUMBER));

        TestUtil.compareArrayOfEntries(expected, ret);
    }

    @Test
    public void deleteDataTest(){
        ArrayList<Entry> ret = md.deleteData((ArrayList<Entry>) expected.clone(), TEST_INDEX, TEST_FILENAME);

        expected.remove(TEST_INDEX);

        TestUtil.compareArrayOfEntries(expected, ret);
    }

    @Test
    public void setFTest(){
        ModuleF newF = mock(ModuleF.class);
        md.setF(newF);
    }

    @Test
    public void setGTest(){
        ModuleG newG = mock(ModuleG.class);
        md.setG(newG);
    }

}
