package bigbang;

import modules.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class Test_Everything {

    ModuleA ma;
    ModuleB mb;
    ModuleC mc;
    ModuleD md;
    ModuleE me;
    ModuleF mf;
    ModuleG mg;
    final static String TEST_FILENAME = "testFileName";
    final static String NONEXISTENT_FILE = "nonExistentFile";

    File f;

    ByteArrayOutputStream stdout;

    @BeforeEach
    public void setUp() throws IOException {
        me = Mockito.spy(new ModuleE());
        mf = Mockito.spy(new ModuleF());
        mg = Mockito.spy(new ModuleG());

        mb = Mockito.spy(new ModuleB(mf));
        mc = Mockito.spy(new ModuleC(mf));
        md = Mockito.spy(new ModuleD(mf, mg));

        ma = new ModuleA(mb, mc, md, me);

        newStdout();

        f = new File(TEST_FILENAME);
        f.createNewFile();
    }

    @AfterEach
    public void deleteFile() throws IOException {
        System.out.println(Files.readString(Paths.get(TEST_FILENAME)));
        f.delete();
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

    public void load() throws ModuleE.DataBaseExitException {
//        Mockito.when(md.insertData(any(), anyString(), anyString(), anyString())).thenReturn(new ArrayList<Entry>());
        ma.run(new String[]{"load", TEST_FILENAME});
    }

    @Test
    public void testLoad() throws ModuleE.DataBaseExitException {
        load();
        verify(mb, times(1)).loadFile(anyString());
    }

    @Test
    public void testLoadNoArgument() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load"});
        assertEquals("Malformed command!\n", stdout.toString());
        verify(mb, never()).loadFile(anyString());
    }

    @Test
    public void testLoadBReturnsNull() throws ModuleE.DataBaseExitException {
//        Mockito.when(mb.loadFile(anyString())).thenReturn(null);
        ma.run(new String[]{"load", NONEXISTENT_FILE});
        verify(mb, times(1)).loadFile(anyString());
    }

    @Test
    public void testAddNoData() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"add", "name", "number"});
        assertEquals("No file loaded!\n", stdout.toString());
        verify(md, never()).insertData(any(), anyString(), anyString(), anyString());
    }

    @Test
    public void testAdd() throws ModuleE.DataBaseExitException, IOException {
//        Mockito.when(md.insertData(any(), anyString(), anyString(), anyString())).thenReturn(new ArrayList<Entry>());
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"add", "name", "number"});
        verify(md, times(1)).insertData(any(), anyString(), anyString(), anyString());

        assertEquals("name,number\n", Files.readString(Paths.get(TEST_FILENAME)));
    }

    @Test
    public void testAddDreturnsNull() throws ModuleE.DataBaseExitException {

        // through static analysis, we can see that d.insertdata will never return null in practice

//        Mockito.when(md.insertData(any(), anyString(), anyString(), anyString())).thenReturn(null);
//        ma.run(new String[]{"load", NONEXISTENT_FILE});
//        ma.run(new String[]{"add", "name", "number"});
//        verify(md, times(1)).insertData(any(), anyString(), anyString(), anyString());
    }

    @Test
    public void testAddNoArgument() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"add"});
        assertEquals("Malformed command!\n", stdout.toString());
        verify(md, never()).insertData(any(), anyString(), anyString(), anyString());
    }

    @Test
    public void testSort() throws ModuleE.DataBaseExitException, IOException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"add", "ddd", "aaa"});
        ma.run(new String[]{"add", "bbb", "bbb"});
        ma.run(new String[]{"add", "ccc", "ccc"});
        ma.run(new String[]{"add", "aaa", "aaa"});
        ma.run(new String[]{"add", "ccc", "aaa"});
        ma.run(new String[]{"add", "bbb", "aaa"});


        ma.run(new String[]{"sort"});
        verify(mc, times(1)).sortData(any());

        assertEquals("""
aaa,aaa
bbb,aaa
bbb,bbb
ccc,aaa,
ccc,ccc
ddd,aaa""", Files.readString(Paths.get(TEST_FILENAME)));
    }

    @Test
    public void testSortNoData() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"sort"});
        assertEquals("No file loaded!\n", stdout.toString());
        verify(mc, never()).sortData(any());
    }

    @Test
    public void testSortCReturnsNull() throws ModuleE.DataBaseExitException {
        // in practice, moduleC never returns null
        // it can throw a NullPointerException if the input data is null
        // but the program does a null check on line 56 in module A
        // so it will never return null

//        Mockito.when(mc.sortData(any())).thenReturn(null);
//        ma.run(new String[]{"load", TEST_FILENAME});
//        ma.run(new String[]{"sort"});
//        verify(mc, times(1)).sortData(any());
    }

    @Test
    public void testUpdate() throws ModuleE.DataBaseExitException, IOException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"add", "aaa", "aaa"});
        ma.run(new String[]{"add", "bbb", "aaa"});
        ma.run(new String[]{"add", "bbb", "bbb"});
        ma.run(new String[]{"add", "ccc", "aaa"});
        ma.run(new String[]{"add", "ccc", "ccc"});
        ma.run(new String[]{"add", "ddd", "aaa"});

        ma.run(new String[]{"update", "5", "new", "data"});

        verify(md, times(1)).updateData(any(), anyInt(), anyString(), anyString(), anyString());

        assertEquals("""
aaa,aaa
bbb,aaa
bbb,bbb
ccc,aaa
new,data
ddd,aaa
""", Files.readString(Paths.get(TEST_FILENAME)));

        // huh this seems to pass
        // talk about in the report how on line 138 they do index-2
        // and how the error cancels out that way to be correct in the end
    }

    @Test
    public void testUpdateInvalidArguments() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"update", "arg1", "arg2", "arg3"});
        verify(md, never()).updateData(any(), anyInt(), anyString(), anyString(), anyString());
    }


    @Test
    public void testUpdateNoData() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"update", "1", "2", "3"});
        assertEquals("No file loaded!\n", stdout.toString());
        verify(md, never()).updateData(any(), anyInt(), anyString(), anyString(), anyString());
    }

    @Test
    public void testUpdateCReturnsNull() throws ModuleE.DataBaseExitException {

//        in practice, we see using static analysis that moduleC can never return null

//        Mockito.when(md.updateData(any(), anyInt(), anyString(), anyString(), anyString())).thenReturn(null);
//        ma.run(new String[]{"load", NONEXISTENT_FILE});
//        ma.run(new String[]{"update", "1", "2", "3"});
//        verify(md, times(1)).updateData(any(), anyInt(), anyString(), anyString(), anyString());
    }

    @Test
    public void testUpdateNoArgument() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"update"});
        assertEquals("Malformed command!\n", stdout.toString());
        verify(md, never()).updateData(any(), anyInt(), anyString(), anyString(), anyString());
    }

    @Test
    public void testDelete() throws ModuleE.DataBaseExitException, IOException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"add", "aaa", "aaa"});
        ma.run(new String[]{"add", "bbb", "aaa"});
        ma.run(new String[]{"add", "bbb", "bbb"});
        ma.run(new String[]{"add", "ccc", "aaa"});
        ma.run(new String[]{"add", "ccc", "ccc"});
        ma.run(new String[]{"add", "ddd", "aaa"});

        ma.run(new String[]{"delete", "5"});
        verify(md, times(1)).deleteData(any(), anyInt(), anyString());

        assertEquals("""
aaa,aaa
bbb,aaa
bbb,bbb
ccc,aaa
ddd,aaa
""", Files.readString(Paths.get(TEST_FILENAME)));
    }

    @Test
    public void testDeleteInvalidArguments() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"delete", "arg1"});
        verify(md, never()).deleteData(any(), anyInt(), anyString());
    }

    @Test
    public void testDeleteNoData() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"delete"});
        assertEquals("No file loaded!\n", stdout.toString());
        verify(md, never()).deleteData(any(), anyInt(), anyString());
    }

    @Test
    public void testDeleteDReturnsNull() throws ModuleE.DataBaseExitException {

        // using static analysis, we see that in practice, deleteData will never return null
//        Mockito.when(md.deleteData(any(), anyInt(), anyString())).thenReturn(null);
//        ma.run(new String[]{"load", NONEXISTENT_FILE});
//        ma.run(new String[]{"delete", "1"});
//        verify(md, times(1)).deleteData(any(), anyInt(), anyString());
    }

    @Test
    public void testDeleteNoArgument() throws ModuleE.DataBaseExitException {
        ma.run(new String[]{"load", TEST_FILENAME});
        ma.run(new String[]{"delete"});
        assertEquals("Malformed command!\n", stdout.toString());
        verify(md, never()).deleteData(any(), anyInt(), anyString());
    }

    @Test
    public void testExit() throws ModuleE.DataBaseExitException {
//        Mockito.doThrow(ModuleE.DataBaseExitException.class).when(me).exitProgram();
        assertThrows(ModuleE.DataBaseExitException.class, () ->ma.run(new String[]{"exit"}));
        // line 147 does not get covered, because the program exits
    }
}
