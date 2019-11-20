package bigbang;
import static org.junit.jupiter.api.Assertions.*;

import modules.ModuleE;
import org.junit.jupiter.api.Test;

public class TestE {

    @Test
    public void testE(){
        assertThrows(ModuleE.DataBaseExitException.class , ()-> new ModuleE().exitProgram());
    }

}
