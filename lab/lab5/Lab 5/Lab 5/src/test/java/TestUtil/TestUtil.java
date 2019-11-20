package TestUtil;

import data.Entry;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;

public class TestUtil {

    public static void compareArrayOfEntries(ArrayList<Entry> expected, ArrayList<Entry> actual){
        Assertions.assertEquals(expected.size(), actual.size());

        for (int i=0; i<expected.size(); i+=1){
            Entry exp = expected.get(i);
            Entry act = actual.get(i);


            if (!exp.getName().equals(act.getName()) || !exp.getNumber().equals(act.getNumber()))
                throw new AssertionFailedError("Arrays don't match", expected, actual);
        }
    }
}
