import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BisectTest {

    @Test
    void runTest61() {
        Bisect b = new Bisect(value -> Math.pow(value, 2) + 1);
        assertThrows(Bisect.RootNotFound.class, () -> b.run(-1, 1));
    }

    @Test
    void runTest67() throws Bisect.RootNotFound {
        Bisect b = new Bisect(value -> Math.pow(value, 2) - 1);
        b.run(-1.5, 0.5);
    }

    @Test
    void runTestMaxIter(){
        Bisect b = new Bisect(1, value -> value + 100);
        assertThrows(Bisect.RootNotFound.class, ()->b.run(-150, 1000000000));
    }

    @Test
    void runTesttolpolynomialconstructor() throws Bisect.RootNotFound {
        Bisect b = new Bisect(50.0, value -> value);
        assertEquals(0, b.run(-10, 10));
    }

    @Test
    void runTesttolmaxiterpolynomialconstructor() throws Bisect.RootNotFound {
        Bisect b = new Bisect(0, 50, value -> value);
        assertEquals(0, b.run(-10, 10));
    }

    @Test
    void runTestGetMaxIterations(){
        Bisect b = new Bisect(50, value->value);
        assertEquals(50, b.getMaxIterations());
    }

    @Test
    void runTestSetMaxIterations(){
        Bisect b = new Bisect(value -> value);
        b.setMaxIterations(50);
        assertEquals(50, b.getMaxIterations());
    }

    @Test
    void runTestGetTolerance(){
        Bisect b = new Bisect(0.0, value->value);
        assertEquals(0.0, b.getTolerance());
    }

    @Test
    void runTestSetTolerance(){
        Bisect b = new Bisect(value -> value);
        b.setTolerance(0.1);
        assertEquals(0.1, b.getTolerance());
    }

}