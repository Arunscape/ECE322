import java.util.Scanner;

// 
// Decompiled by Procyon v0.5.36
// 

public class Main
{
    public static void main(final String[] args) {
        boolean running = true;
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Drone simulator, k = 100.");
        while (running) {
            System.out.print("drone> ");
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
        if (params.length != 3) {
            System.out.println("ERROR: Incorrect number of arguments.");
            return;
        }
        final int[] values = new int[3];
        for (int i = 0; i < params.length; ++i) {
            try {
                values[i] = Integer.parseInt(params[i]);
            }
            catch (NumberFormatException e) {
                System.out.println("ERROR: Invaid argument - non integer");
                return;
            }
        }
        final int a = values[0];
        final int b = values[1];
        final int c = values[2];
        if (a < 0 || c < 0) {
            System.out.println("ERROR: Invald argument - negative value");
        }
        else if (a + b + c <= 100) {
            System.out.println("Success!");
        }
        else {
            System.out.println("Failure!");
        }
    }
}
