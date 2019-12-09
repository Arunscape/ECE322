import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CommitTest{

    @Test
    public void randomTest() {
        // fails because numbers that are out of bounds are being generated

        Random r = new Random();

        for (int i=0; i<1000; i+=1){
            int n = Math.abs(r.nextInt(1001)) + 1; //positive, non
            double a = r.nextDouble();
            double b = a + r.nextDouble();
            double[] res = Commit.random(n, a, b);

            Arrays.sort(res);
            assertTrue(res.length == n, "Length not equal to n");
            assertTrue(a <= res[0], "A random number below the lower bound a was generated. a: " + a + " , number: " + res[0]);
            assertTrue(res[n - 1] <=b, "A random number higher than the upper bound was generated. b: " + b + " , number: " + res[n-1]);
        }
    }

    @Test
    public void maxTest(){
        Random r = new Random();

        for (int i=0; i<1000; i+=1){
            int n = Math.abs(r.nextInt(1001))+1; // positive, nonzero
            double input[] = new double[n];
            for (int j=0; j < n; j+=1)
                input[j] = r.nextDouble();

            Arrays.sort(input);
            assertEquals(input[n-1], Commit.max(input));
        }
    }

    @Test
    public void minTest(){
        Random r = new Random();

        for (int i=0; i<1000; i+=1){
            int n = Math.abs(r.nextInt(1001))+1; // positive, nonzero
            double input[] = new double[n];
            for (int j=0; j < n; j+=1)
                input[j] = r.nextDouble();

            Arrays.sort(input);
            assertEquals(input[0], Commit.min(input));
        }
    }

    @Test
    public void normalizeTest(){
        // fails because line 58 they do min-values, which results in "normalization" between [0, -1] not [0, 1]
        double[] input1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] input2 = {0, 33, 66, 100};

        double[] ret1 = Commit.normalize(input1);
        double[] exp1 = {0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
        assertArrayEquals(exp1, ret1);

        double[] ret2 = Commit.normalize(input2);
        double[] exp2 = {0, 0.33, 0.66, 1.0};
        assertArrayEquals(exp2, ret2);
    }

    @Test
    public void sumTest() {

        Random r = new Random();

        for (int i=0; i<1000; i+=1){
            int n = Math.abs(r.nextInt(1001))+1; // positive, nonzero
            double input[] = new double[n];
            for (int j=0; j < n; j+=1)
                input[j] = r.nextDouble();

            assertEquals(Arrays.stream(input).sum(), Commit.sum(input), 1e-10); // tolerance of 1e-10 because of error that accumilates with double
        }
    }

    @Test
    public void stddevTest(){
        double[] input1 = {0.8448535275473217, 0.7356655820407797, 0.9431584337138527, 0.9885039386933584, 0.26752295318703023, 0.30118478380150293, 0.5614795756611817, 0.27410185661451103, 0.3727308526619961, 0.9350197461150006, 0.3430515987014403, 0.14325655442623153, 0.09474732578722389, 0.8118925398379901, 0.7889524243808093, 0.6174482765472112, 0.08282552694523304, 0.9906861613488818, 0.1006718689797883, 0.07076982982755198};
        double exp1 = 0.33383429942515;

        double[] input2 = {24.351879794282766, -1.2369080960006045, 21.33618933269294, 38.40700888177969, 81.07224984081901, 14.773395426428706, -23.465737641671126, -29.74766797865162, -80.53273274920562, 82.69835022556401, 16.765612733609586, 85.27325420746189, -25.56842729615137, -85.17577250342885, 91.14866661682103, 47.56747687575623, 71.62517118559902, 26.263337092658247, -78.61956099645082, -18.809917650844213, 31.415431556119216, 15.021802528535659, -45.50777659561043, 76.15463305868838, 71.73129701754698, 42.00719896644702, 98.93219072662015, 29.911361379866946, -7.304486822031862, 72.46129116466469, 25.17287827553399, 6.610331737331364, 34.267027445622745, 10.796643684552663, -44.6877977606748, 99.08890435845663, 18.379318834519594, 54.01219466707613, -11.329038630356479, -23.230019858073646, 33.03020694951988, -59.205375268977974, -8.495912401158435, 83.71104890003406, 80.19371195739095, 70.17088288687546, -27.756415190854483, 67.22645204084466, 2.237307845125258, 90.69398038338696, 13.575182881518089, 66.0876889384611, -30.1366615029317, -20.084766272685243, 10.62213430500914, 70.12939720918465, -53.19292944066913, -84.43071331549132, -53.30861375844398, 36.75691360838863, 4.481177488201666, 89.23026437856967, 28.481208229172694, 4.041593290473799, -19.144188062781325, 71.48911820023875, -97.3407210733165, -82.95697886617639, -52.019808046281035, -20.500795201979386, -65.75240723052059, -76.4564498827019, -48.255740133965006, 20.75249609534157, -45.919570907416606, 55.70989509490502, -30.432532466301396, -51.16117165251921, -7.213072379759879, 62.480197271840865, -14.266598956238383, -14.496858737726953, -6.199152015512155, 86.73375309074737, 24.576298742407715, -50.335938636762336, -98.21921344917936, -97.29469852961215, 92.13635661651074, -6.363254915974139, 34.622868801612384, -47.36683311550864, -57.59764296582546, 53.28248250936096, 26.007936605144735, -86.505212849109, -7.68902146399742, -18.99542397736265, -5.991562076158388, 82.91883854718108};
        double exp2 = 54.008916067881;

        double ret1 = Commit.stddev(input1);
        assertEquals(exp1, ret1, 1e-10);

        double ret2 = Commit.stddev(input2);
        assertEquals(exp2, ret2, 1e-10);
    }

    @Test
    public void arrayAddTestSameLength() {

        Random r = new Random();

        for (int i=0; i<1000; i+=1){
            int n = Math.abs(r.nextInt(1001))+1; // positive, nonzero
            double d1[] = new double[n];
            for (int a=0; a < n; a+=1)
                d1[a] = r.nextDouble();

            double d2[] = new double[n];
            for (int b=0; b < n; b+=1)
                d2[b] = r.nextDouble();

            double[] expected = new double[n];
            Arrays.setAll(expected, index -> d1[index] + d2[index]);
            assertArrayEquals(expected, Commit.arrayAdd(d1, d2), 1e-10); // tolerance of 1e-10 because of error that accumilates with double
        }
    }

    @Test
    public void arrayAddTestDifferentLength(){
        double[] d1 = {1, 2, 3, 4, 5, 6};
        double[] d2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        assertThrows(AssertionError.class, () -> Commit.arrayAdd(d1, d2));
        assertThrows(AssertionError.class, () -> Commit.arrayAdd(d2, d1));

    }

    @Test
    public void negateTest(){
        Random r = new Random();

        for (int i=0; i<1000; i+=1){
            int n = Math.abs(r.nextInt(1001))+1; // positive, nonzero
            double input[] = new double[n];
            for (int j=0; j < n; j+=1)
                input[j] = r.nextDouble();

            assertArrayEquals(Arrays.stream(input).map(x->-x).toArray(), Commit.arrayNegate(input));
        }
    }

    @Test
    public void arraySubractTestSameLength() {

        Random r = new Random();

        for (int i=0; i<1000; i+=1){
            int n = Math.abs(r.nextInt(1001))+1; // positive, nonzero
            double d1[] = new double[n];
            for (int a=0; a < n; a+=1)
                d1[a] = r.nextDouble();

            double d2[] = new double[n];
            for (int b=0; b < n; b+=1)
                d2[b] = r.nextDouble();

            double[] expected = new double[n];
            Arrays.setAll(expected, index -> d1[index] - d2[index]);
            assertArrayEquals(expected, Commit.arraySubtract(d1, d2), 1e-10); // tolerance of 1e-10 because of error that accumilates with double
        }
    }

    @Test
    public void arraySubtractTestDifferentLength(){
        double[] d1 = {1, 2, 3, 4, 5, 6};
        double[] d2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        assertThrows(AssertionError.class, () -> Commit.arraySubtract(d1, d2));
        assertThrows(AssertionError.class, () -> Commit.arraySubtract(d2, d1));

    }

    @Test
    public void distanceTest(){
        // different lengths
        assertThrows(AssertionError.class, () -> Commit.distance(new double[]{1}, new double[]{1,2}));
        assertThrows(AssertionError.class, () -> Commit.distance(new double[]{}, new double[]{1, 2}));

        // more than 2 dimensions
        assertThrows(AssertionError.class, ()-> Commit.distance(new double[]{1, 2, 3}, new double[]{4, 5, 6}));

        double ret1 = Commit.distance(new double[]{-1, -2}, new double[]{3, 4});
        double ret2 = Commit.distance(new double[]{6, 7}, new double[]{-8, -9});

        assertEquals(Math.sqrt(52), ret1);
        assertEquals(Math.sqrt(452), ret2);
    }

    @Test
    public void arrayDeviationTest(){
        double[] input1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] input2 = {4, 8, 1, 3, 9, 5, 10, 2, 7, 6};


        double[] expected1 = {-4.5, -3.5, -2.5, -1.5, -0.5, 0.5, 1.5, 2.5, 3.5, 4.5};
        assertArrayEquals(expected1, Commit.arrayDeviation(input1));

        double[] expected2 = {-1.5, 2.5, -4.5, -2.5, 3.5, -0.5, 4.5, -3.5, 1.5, 0.5} ;
        assertArrayEquals(expected2, Commit.arrayDeviation(input2));

    }

}