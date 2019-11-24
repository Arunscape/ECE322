package bottumUp;

import TestUtil.TestUtil;
import data.Entry;
import modules.ModuleD;
import modules.ModuleF;
import modules.ModuleG;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class Test04_DFG {

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
        mf = spy(new ModuleF());
        mg = spy(new ModuleG());

        md = new ModuleD(mf, mg);

        expected = new ArrayList<>() {{
            for (int i = 0; i < 10; i += 1)
                add(new Entry(TEST_NAME + i, TEST_NUMBER + i));
        }};
    }

    @AfterEach
    public void after(TestInfo testInfo){
        if(testInfo.getTags().contains("SkipAfter")) {
            return;
        }
        verify(mf, times(1)).displayData(any());
        verify(mg, times(1)).updateData(anyString(), any());
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

    @Tag("SkipAfter")
    @Test
    public void setFTest(){
        ModuleF newF = new ModuleF();
        md.setF(newF);
    }

    @Tag("SkipAfter")
    @Test
    public void setGTest(){
        ModuleG newG = new ModuleG();
        md.setG(newG);
    }

}
