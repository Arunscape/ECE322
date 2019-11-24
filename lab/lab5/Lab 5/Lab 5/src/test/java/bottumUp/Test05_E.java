package bottumUp;

import modules.ModuleE;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test05_E {

    @Test
    public void testE(){
        assertThrows(ModuleE.DataBaseExitException.class , ()-> new ModuleE().exitProgram());
    }

}
