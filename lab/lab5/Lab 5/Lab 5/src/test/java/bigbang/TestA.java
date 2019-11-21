package bigbang;

import modules.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;


public class TestA {

    ModuleA ma;
    ModuleB mb;
    ModuleC mc;
    ModuleD md;
    ModuleE me;

    ByteArrayOutputStream stdout;

    @BeforeEach
    public void setUp(){
        mb = Mockito.mock(ModuleB.class);
        mc = Mockito.mock(ModuleC.class);
        md = Mockito.mock(ModuleD.class);
        me = Mockito.mock(ModuleE.class);

        ma = new ModuleA(mb, mc, md, me);

        newStdout();

    }

    public void newStdout(){
        stdout = new ByteArrayOutputStream();
        ma.setOutputStream(new PrintStream(stdout));
    }

    @Test
    public void testHelp() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"help"});

        final String help = "Available Commands: \n" +
                "load <filepath>\n" +
                "add <name> <number>\n" +
                "update <index> <name> <number>\n" +
                "delete <index>\n" +
                "sort\n" +
                "exit";

        assertEquals(help + "\n", stdout.toString());
    }

    @Test
    public void testLoad() throws ModuleE.DataBaseExitException {

        final String TEST_FILENAME = "testFileName";

        ma.run(new String[]{"load", TEST_FILENAME});
    }

    @Test
    public void testLoadNoArgument() throws ModuleE.DataBaseExitException {

            ma.run(new String[]{"load"});
            assertEquals("Malformed command!\n", stdout.toString());


    }

    @Test
    public void testLoadBReturnsNull() throws ModuleE.DataBaseExitException {
        Mockito.when(mb.loadFile(anyString())).thenReturn(null);
        final String TEST_FILENAME = "testFileName";

        ma.run(new String[]{"load", TEST_FILENAME});
    }

}
