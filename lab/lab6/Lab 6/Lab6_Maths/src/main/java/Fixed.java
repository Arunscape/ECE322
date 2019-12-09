import java.util.Arrays;
import java.util.Random;

public class Fixed {

    /**
     * Creates an array of n random values in the range [a,b]
     *
     * @param n Number of values to generate
     * @param a lower bound
     * @param b upper bound
     * @return Array of random values
     */
    public static double[] random(int n, double a, double b) {
        double[] values = new double[n];
        for (int i = 0; i < values.length; i++)
            values[i] = a + (Math.random() * (b - a));

        return values;
    }

    /**
     * Returns the maximum values contained in the passed array
     *
     * @param values Array to search in
     * @return Highest value in passed array
     */
    public static double max(double[] values) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (max < values[i])
                max = values[i];
        }
        return max;
    }

    /**
     * Returns the minimum values of an array
     *
     * @param values Array to search through
     * @return Smallest value in the array
     */
    public static double min(double[] values) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (min > values[i])
                min = values[i];
        }
        return min;
    }

    /**
     * Normalizes the values in the passed array to [0,1]
     *
     * @param values Array to be normalized
     * @return Normalized array
     */
    public static double[] normalize(double[] values) {
        double max = max(values);
        double min = min(values);
        double[] normalized = new double[values.length];

        for (int i = 0; i < values.length; i++)
            normalized[i] = (values[i] - min) / (max - min);

        return normalized;
    }

    /**
     * Calculates the sum of the array elements
     *
     * @param values Array to sum
     * @return summed value
     */
    public static double sum(double[] values) {
        double sum = 0.0;
        for (int i = 0; i < values.length; i++)
            sum += values[i];
        return sum;
    }

    /**
     * Calculates the standard deviation of the values in the array
     *
     * @param values Array to calculate deviation of
     * @return standard deviation
     */
    public static double stddev(double[] values) {
        double mean = sum(values) / values.length;
        double variance = 0;
        for (int i = 0; i < values.length; i++)
            variance += Math.pow(values[i] - mean, 2);
        return Math.sqrt(variance / values.length);
    }

    /**
     * Adds two arrays together, element-wise
     *
     * @param d1 first array
     * @param d2 second array
     * @return result of addition
     */
    public static double[] arrayAdd(double[] d1, double[] d2) {
        assert d1.length == d2.length;

        double[] result = new double[d1.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = d1[i] + d2[i];
        }
        return result;
    }

    /**
     * Negates the values in the array
     *
     * @param d values
     * @return result
     */
    public static double[] arrayNegate(double[] d) {
        return Arrays.stream(d).map(x -> -x).toArray();
    }

    /**
     * Subtracts two arrays element-wise
     *
     * @param d1 first array
     * @param d2 second array
     * @return result of subtraction
     */
    public static double[] arraySubtract(double[] d1, double[] d2) {
        assert d1.length == d2.length;
        double[] ret = new double[Math.max(d1.length, d2.length)];
        Arrays.setAll(ret, index -> d1[index] - d2[index]);
        return ret;
    }

    /**
     * Calculates the Cartesian distance between points defined by d1 and d2
     *
     * @param d1 first point
     * @param d2 second point
     * @return Cartesian distance
     */
    public static double distance(double[] d1, double[] d2) {
        assert d1.length == 2;
        assert d2.length == 2;

        return Math.sqrt(Math.pow(d2[0] - d1[0], 2) + Math.pow(d2[1] - d1[1], 2));
    }

    /**
     * Calculates an array representing the deviation each value in
     * the array is from the mean value of the set
     *
     * @param d1 input array
     * @return deviation values array
     */
    public static double[] arrayDeviation(double[] d1) {
        double mean = Arrays.stream(d1).sum() / d1.length;

        double[] result = new double[d1.length];
        Arrays.setAll(result, index -> d1[index] - mean);
        return result;
    }

}
