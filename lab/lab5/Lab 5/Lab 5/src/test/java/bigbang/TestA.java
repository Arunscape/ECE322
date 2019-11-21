package bigbang;

import modules.ModuleB;
import modules.ModuleC;
import modules.ModuleD;
import modules.ModuleE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class TestA {

    ModuleB mb;
    ModuleC mc;
    ModuleD md;
    ModuleE me;

    @BeforeEach
    public void setUp(){
        mb = Mockito.mock(ModuleB.class);
        mc = Mockito.mock(ModuleC.class);
        md = Mockito.mock(ModuleD.class);
        me = Mockito.mock(ModuleE.class);
    }

    @Test

}
