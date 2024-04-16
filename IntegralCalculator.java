import java.util.Scanner;
import java.util.function.Function;

public class IntegralCalculator {

    // Method to calculate a single integral using the trapezoidal rule
    public static double singleIntegral(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n; // Calculate width of each subdivision
        double sum = 0.5 * (f.apply(a) + f.apply(b)); // Initialize sum with endpoints
        for (int i = 1; i < n; i++) {
            double x = a + i * h; // Calculate x value for current subdivision
            sum += f.apply(x); // Add function value at x to sum
        }
        return h * sum; // Return the result of the trapezoidal rule
    }

    // Method to calculate a double integral using the trapezoidal rule
    public static double doubleIntegral(Function<double[], Double> f, double[] a, double[] b, int[] n) {
        double hx = (b[0] - a[0]) / n[0]; // Width of subdivision in the x-dimension
        double hy = (b[1] - a[1]) / n[1]; // Width of subdivision in the y-dimension
        double sum = 0.0; // Initialize sum
        for (int i = 0; i < n[0]; i++) {
            for (int j = 0; j < n[1]; j++) {
                double x = a[0] + i * hx; // Calculate x value for current subdivision
                double y = a[1] + j * hy; // Calculate y value for current subdivision
                double[] point = {x, y}; // Array representing current point
                double value = f.apply(point); // Calculate function value at current point
                sum += value; // Add function value to sum
            }
        }
        return hx * hy * sum; // Return the result of the double integral
    }

    // Method to calculate a triple integral using the trapezoidal rule
    public static double tripleIntegral(Function<double[], Double> f, double[] a, double[] b, int[] n) {
        double hx = (b[0] - a[0]) / n[0]; // Width of subdivision in the x-dimension
        double hy = (b[1] - a[1]) / n[1]; // Width of subdivision in the y-dimension
        double hz = (b[2] - a[2]) / n[2]; // Width of subdivision in the z-dimension
        double sum = 0.0; // Initialize sum
        for (int i = 0; i < n[0]; i++) {
            for (int j = 0; j < n[1]; j++) {
                for (int k = 0; k < n[2]; k++) {
                    double x = a[0] + i * hx; // Calculate x value for current subdivision
                    double y = a[1] + j * hy; // Calculate y value for current subdivision
                    double z = a[2] + k * hz; // Calculate z value for current subdivision
                    double[] point = {x, y, z}; // Array representing current point
                    double value = f.apply(point); // Calculate function value at current point
                    sum += value; // Add function value to sum
                }
            }
        }
        return hx * hy * hz * sum; // Return the result of the triple integral
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What type of integral do you need for your problem?");
        System.out.println("1. Single Integral");
        System.out.println("2. Double Integral");
        System.out.println("3. Triple Integral");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                // Single integral
                System.out.println("Enter the function for the single integral (e.g., x^2):");
                String singleFunction = scanner.next();
                Function<Double, Double> f1 = x -> {
                    try {
                        return Double.parseDouble(singleFunction.replaceAll("x", String.valueOf(x)));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid function format. Please enter a valid function.");
                        return Double.NaN;
                    }
                };

                double a1, b1;
                int n1;
                try {
                    System.out.println("Enter the lower limit (a) for the single integral:");
                    a1 = scanner.nextDouble();
                    System.out.println("Enter the upper limit (b) for the single integral:");
                    b1 = scanner.nextDouble();
                    System.out.println("Enter the number of subdivisions (n) for the single integral:");
                    n1 = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input format. Please enter valid numeric values.");
                    return;
                }

                double result1 = singleIntegral(f1, a1, b1, n1);
                System.out.println("Single Integral Result: " + result1);
                break;

            case 2:
                // Double integral
                System.out.println("Enter the function for the double integral (e.g., x^2 + y^2):");
                String doubleFunction = scanner.next();
                Function<double[], Double> f2 = point -> {
                    try {
                        double x = point[0];
                        double y = point[1];
                        return Double.parseDouble(doubleFunction.replaceAll("x", String.valueOf(x)).replaceAll("y", String.valueOf(y)));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid function format. Please enter a valid function.");
                        return Double.NaN;
                    }
                };

                double[] a2 = new double[2];
                double[] b2 = new double[2];
                int[] n2 = new int[2];

                try {
                    System.out.println("Enter the lower limit (a) for the double integral (x):");
                    a2[0] = scanner.nextDouble();
                    System.out.println("Enter the upper limit (b) for the double integral (x):");
                    b2[0] = scanner.nextDouble();
                    System.out.println("Enter the number of subdivisions (n) for the double integral (x):");
                    n2[0] = scanner.nextInt();

                    System.out.println("Enter the lower limit (a) for the double integral (y):");
                    a2[1] = scanner.nextDouble();
                    System.out.println("Enter the upper limit (b) for the double integral (y):");
                    b2[1] = scanner.nextDouble();
                    System.out.println("Enter the number of subdivisions (n) for the double integral (y):");
                    n2[1] = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input format. Please enter valid numeric values.");
                    return;
                }

                double result2 = doubleIntegral(f2, a2, b2, n2);
                System.out.println("Double Integral Result: " + result2);
                break;

            case 3:
                // Triple integral
                System.out.println("Enter the function for the triple integral (e.g., x^2 + y^2 + z^2):");
                String tripleFunction = scanner.next();
                Function<double[], Double> f3 = point -> {
                    try {
                        double x = point[0];
                        double y = point[1];
                        double z = point[2];
                        return Double.parseDouble(tripleFunction.replaceAll("x", String.valueOf(x)).replaceAll("y", String.valueOf(y)).replaceAll("z", String.valueOf(z)));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid function format. Please enter a valid function.");
                        return Double.NaN;
                    }
                };

                double[] a3 = new double[3];
                double[] b3 = new double[3];
                int[] n3 = new int[3];

                try {
                    System.out.println("Enter the lower limit (a) for the triple integral (x):");
                    a3[0] = scanner.nextDouble();
                    System.out.println("Enter the upper limit (b) for the triple integral (x):");
                    b3[0] = scanner.nextDouble();
                    System.out.println("Enter the number of subdivisions (n) for the triple integral (x):");
                    n3[0] = scanner.nextInt();

                    System.out.println("Enter the lower limit (a) for the triple integral (y):");
                    a3[1] = scanner.nextDouble();
                    System.out.println("Enter the upper limit (b) for the triple integral (y):");
                    b3[1] = scanner.nextDouble();
                    System.out.println("Enter the number of subdivisions (n) for the triple integral (y):");
                    n3[1] = scanner.nextInt();

                    System.out.println("Enter the lower limit (a) for the triple integral (z):");
                    a3[2] = scanner.nextDouble();
                    System.out.println("Enter the upper limit (b) for the triple integral (z):");
                    b3[2] = scanner.nextDouble();
                    System.out.println("Enter the number of subdivisions (n) for the triple integral (z):");
                    n3[2] = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input format. Please enter valid numeric values.");
                    return;
                }

                double result3 = tripleIntegral(f3, a3, b3, n3);
                System.out.println("Triple Integral Result: " + result3);
                break;

            default:
                System.out.println("Invalid choice. Please choose 1, 2, or 3.");
        }

        scanner.close();
    }
}
