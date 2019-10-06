import java.util.Scanner;

// 
// Decompiled by Procyon v0.5.36
// 

public class Drone
{
    public static void main(final String[] args) {
        boolean running = true;
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Remote controlled car: r = 1");
        while (running) {
            System.out.print("remote> ");
            final String s = scanner.nextLine();
            if (s.equals("exit")) {
                running = false;
            }
            else {
                final String[] params = s.split(" ");
                parseParams(params);
            }
        }
    }
    
    public static void parseParams(final String[] params) {
        if (params.length != 2) {
            System.out.println("ERROR: Incorrect number of arguments.");
            return;
        }
        final double[] values = new double[2];
        for (int i = 0; i < params.length; ++i) {
            try {
                values[i] = Double.parseDouble(params[i]);
            }
            catch (NumberFormatException e) {
                System.out.println("ERROR: Invaid argument - not a number");
                return;
            }
        }
        final double a = values[0];
        final double b = values[1];
        final double distance = Math.sqrt(Math.pow(a, 2.0) + Math.pow(b, 2.0));
        if (distance <= 1.0) {
            System.out.println("Ok.");
        }
        else {
            System.out.println("Out of range!");
        }
    }
}
