package bigbang;

import data.Entry;
import modules.ModuleD;
import modules.ModuleF;
import modules.ModuleG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;



public class TestD {

    public ModuleF mf;
    public ModuleG mg;
    ModuleD md;

    @BeforeEach
    public void setUp(){
        mf = mock(ModuleF.class);
        mg = mock(ModuleG.class);

        md = new ModuleD(mf, mg);
    }

    @Test
    public void insertDataTest(){
        md.insertData(new ArrayList<Entry>(), "testName", "testString", "testFileName");
    }
}
