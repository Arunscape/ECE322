package bigbang;

import TestUtil.TestUtil;
import data.Entry;
import modules.ModuleC;
import modules.ModuleF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class TestC {

    ModuleF mf;
    ModuleC mc;

    @BeforeEach
    public void setUp(){
        mf = mock(ModuleF.class);
        mc = new ModuleC(mf);
    }

    @Test
    public void sortDataTest(){

        final String TEST_NAME = "testName";
        final String TEST_NUMBER = "testNumber";

        ArrayList<Entry> unsorted = new ArrayList<>() {{
            add(new Entry("ddd", "aaa"));
            add(new Entry("bbb", "bbb"));
            add(new Entry("ccc", "ccc"));
            add(new Entry("aaa", "aaa"));
            add(new Entry("ccc", "aaa"));
            add(new Entry("bbb", "aaa"));
        }};

        ArrayList<Entry> sorted = new ArrayList<>() {{
            add(new Entry("aaa", "aaa"));
            add(new Entry("bbb", "aaa"));
            add(new Entry("bbb", "bbb"));
            add(new Entry("ccc", "aaa"));
            add(new Entry("ccc", "ccc"));
            add(new Entry("ddd", "aaa"));
        }};
        ArrayList<Entry> ret = mc.sortData(unsorted);

        TestUtil.compareArrayOfEntries(sorted, ret);
    }

    @Test
    public void setFTest(){
        ModuleF newF = mock(ModuleF.class);
        mc.setF(newF);
    }

    // to cover line 28 in ModuleC
    @Test
    public void sortFourElementsTest(){
        ArrayList<Entry> unsorted = new ArrayList<>() {{
            add(new Entry("ccc", "ccc"));
            add(new Entry("aaa", "aaa"));
            add(new Entry("bbb", "ddd"));
            add(new Entry("bbb", "aaa"));
        }};

        ArrayList<Entry> sorted = new ArrayList<>() {{
            add(new Entry("aaa", "aaa"));
            add(new Entry("bbb", "aaa"));
            add(new Entry("bbb", "ddd"));
            add(new Entry("ccc", "ccc"));
        }};

        ArrayList<Entry> ret = mc.sortData(unsorted);

        TestUtil.compareArrayOfEntries(sorted, ret);
    }

}
