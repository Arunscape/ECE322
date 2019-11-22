package bigbang;

import data.Entry;
import modules.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;


public class TestA {

    ModuleA ma;
    ModuleB mb;
    ModuleC mc;
    ModuleD md;
    ModuleE me;
    final static String TEST_FILENAME = "testFileName";

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


        ma.run(new String[]{"load", TEST_FILENAME});
    }

    @Test
    public void testAddNoData() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"add", "name", "number"});
        assertEquals("No file loaded!\n", stdout.toString());
    }

    @Test
    public void testAdd() throws ModuleE.DataBaseExitException {
        Mockito.when(md.insertData(any(), anyString(), anyString(), anyString())).thenReturn(new ArrayList<Entry>());
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"add", "name", "number"});
    }

    @Test
    public void testAddDreturnsNull() throws ModuleE.DataBaseExitException {
        Mockito.when(md.insertData(any(), anyString(), anyString(), anyString())).thenReturn(null);
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"add", "name", "number"});
    }

    @Test
    public void testAddNoArgument() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"add"});
        assertEquals("Malformed command!\n", stdout.toString());
    }

    @Test
    public void testSort() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"sort"});
    }

    @Test
    public void testSortNoData() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"sort"});
        assertEquals("No file loaded!\n", stdout.toString());
    }

    @Test
    public void testSortCReturnsNull() throws ModuleE.DataBaseExitException {
        Mockito.when(mc.sortData(any())).thenReturn(null);
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"sort"});
    }

    @Test
    public void testUpdate() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"update", "1", "2", "3"});
    }

    @Test
    public void testUpdateInvalidArguments() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"update", "arg1", "arg2", "arg3"});
    }


    @Test
    public void testUpdateNoData() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"update", "1", "2", "3"});
        assertEquals("No file loaded!\n", stdout.toString());
    }

    @Test
    public void testUpdateCReturnsNull() throws ModuleE.DataBaseExitException {
        Mockito.when(md.updateData(any(), anyInt(), anyString(), anyString(), anyString())).thenReturn(null);
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"update", "1", "2", "3"});
    }

    @Test
    public void testUpdateNoArgument() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"update"});
        assertEquals("Malformed command!\n", stdout.toString());
    }

    @Test
    public void testDelete() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"delete", "1"});
    }

    @Test
    public void testDeleteInvalidArguments() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"delete", "arg1"});
    }

    @Test
    public void testDeleteNoData() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"delete"});
        assertEquals("No file loaded!\n", stdout.toString());
    }

    @Test
    public void testDeleteDReturnsNull() throws ModuleE.DataBaseExitException {
        Mockito.when(md.deleteData(any(), anyInt(), anyString())).thenReturn(null);
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"delete", "1"});
    }

    @Test
    public void testDeleteNoArgument() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"delete"});
        assertEquals("Malformed command!\n", stdout.toString());
    }

    @Test
    public void testExit() throws ModuleE.DataBaseExitException {
        Mockito.doThrow(ModuleE.DataBaseExitException.class).when(me).exitProgram();
        assertThrows(ModuleE.DataBaseExitException.class, () ->ma.run(new String[]{"exit"}));
        // line 147 does not get covered, because the program exits
    }


}
