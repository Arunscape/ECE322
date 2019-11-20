import data.Entry;
import org.junit.jupiter.api.Test;

public class EntryTest {

    final Entry e = new Entry("testName", "testNumber");

    @Test
    public void compareToSameNameDifferentNumber(){
        Entry other = new Entry(e.getName(), "differentNumber");

        assert e.compareTo(other) != 0;
    }

    @Test
    public void compareToSameNameandNumber(){
        Entry other = new Entry(e.getName(), e.getNumber());

        assert e.compareTo(other) == 0;
    }

    @Test
    public void compareToDifferentNameSameNumber(){
        Entry other = new Entry("differentName", e.getNumber());

        assert e.compareTo(other) != 0;
    }

    @Test
    public void compareToDifferent(){
        Entry other = new Entry("differentName", "differentNumber");

        assert e.compareTo(other) != 0;

    }
}
