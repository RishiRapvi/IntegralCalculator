

import org.apache.commons.math4.legacy.analysis.UnivariateFunction;
import org.apache.commons.math4.legacy.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math4.legacy.analysis.integration.UnivariateIntegrator;
import org.apache.commons.math4.legacy.analysis.polynomials.PolynomialFunction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntegralCalculator {

    // Method to perform indefinite integration
    public static double indefiniteIntegration(UnivariateFunction f) {
        UnivariateIntegrator integrator = new SimpsonIntegrator();
        return integrator.integrate(1000, f, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    // Method to perform definite integration
    public static double definiteIntegration(UnivariateFunction f, double lowerBound, double upperBound) {
        UnivariateIntegrator integrator = new SimpsonIntegrator();
        return integrator.integrate(1000, f, lowerBound, upperBound);
    }

    // Method to perform double integration (definite or indefinite)
    public static double doubleIntegration(UnivariateFunction f, double lowerX, double upperX, UnivariateFunction g, double lowerY, double upperY, boolean definite) {
        UnivariateIntegrator integrator = new SimpsonIntegrator();
        UnivariateFunction innerIntegral = y -> definite ? definiteIntegration(g, lowerY, upperY) : indefiniteIntegration(g);
        return integrator.integrate(1000, x -> definite ? definiteIntegration(f, lowerX, upperX) * innerIntegral.value(x) : indefiniteIntegration(f), lowerX, upperX);
    }

    // Method to perform triple integration (definite or indefinite)
    public static double tripleIntegration(UnivariateFunction f, double lowerX, double upperX, UnivariateFunction g, double lowerY, double upperY, UnivariateFunction h, double lowerZ, double upperZ, boolean definite) {
        UnivariateIntegrator integrator = new SimpsonIntegrator();
        UnivariateFunction innerIntegral = z -> definite ? definiteIntegration(h, lowerZ, upperZ) : indefiniteIntegration(h);
        return integrator.integrate(1000, y -> doubleIntegration(f, lowerX, upperX, g, lowerY, upperY, definite) * innerIntegral.value(y), lowerY, upperY);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Select the type of integration:");
            System.out.println("1. Indefinite Integral");
            System.out.println("2. Definite Integral");
            System.out.println("3. Double Integral (Definite)");
            System.out.println("4. Double Integral (Indefinite)");
            System.out.println("5. Triple Integral (Definite)");
            System.out.println("6. Triple Integral (Indefinite)");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (option) {
                case 1:
                    System.out.println("Enter the function for indefinite integration (in terms of x):");
                    String functionString = scanner.nextLine();

                    // Handle the case when the function is just "x"
                    if (functionString.equals("x")) {
                        System.out.println("Indefinite Integral of 'x' is (x^2)/2 + C");
                        return;
                    }

                    // Parse the string representing a polynomial expression into an array of coefficients
                    double[] coefficients = parsePolynomialCoefficients(functionString);

                    // Define the function for indefinite integration
                    UnivariateFunction function = new PolynomialFunction(coefficients);

                    // Perform indefinite integration
                    double result = indefiniteIntegration(function);

                    System.out.println("Indefinite Integral Result: " + result);
                    break;
                case 2:
                    System.out.println("Enter the function for definite integration (in terms of x):");
                    String definiteFunctionString = scanner.nextLine();

                    System.out.println("Enter the lower bound for definite integration:");
                    double definiteLowerBound = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline character

                    System.out.println("Enter the upper bound for definite integration:");
                    double definiteUpperBound = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline character

                    // Parse the string representing a polynomial expression into an array of coefficients
                    double[] definiteCoefficients = parsePolynomialCoefficients(definiteFunctionString);

                    // Define the function for definite integration
                    UnivariateFunction definiteFunction = new PolynomialFunction(definiteCoefficients);

                    // Perform definite integration
                    double definiteResult = definiteIntegration(definiteFunction, definiteLowerBound, definiteUpperBound);

                    System.out.println("Definite Integral Result: " + definiteResult);
                    break;
                case 3:
                    System.out.println("Enter the function f(x) for double integration (in terms of x):");
                    String doubleFunctionStringX = scanner.nextLine();

                    System.out.println("Enter the lower bound for integration in x direction:");
                    double doubleLowerBoundX = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline character

                    System.out.println("Enter the upper bound for integration in x direction:");
                    double doubleUpperBoundX = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline character

                    System.out.println("Enter the function g(y) for double integration (in terms of y):");
                    String doubleFunctionStringY = scanner.nextLine();

                    System.out.println("Enter the lower bound for integration in y direction:");
                    double doubleLowerBoundY = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline character

                    System.out.println("Enter the upper bound for integration in y direction:");
                    double doubleUpperBoundY = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline character

                    // Parse the string representing a polynomial expression into an array of coefficients for function f(x)
                    double[] doubleCoefficientsX = parsePolynomialCoefficients(doubleFunctionStringX);

                    // Define the function for double integration in x direction
                    UnivariateFunction doubleFunctionX = new PolynomialFunction(doubleCoefficientsX);

                    // Parse the string representing a polynomial expression into an array of coefficients for function g(y)
                    double[] doubleCoefficientsY = parsePolynomialCoefficients(doubleFunctionStringY);

                    // Define the function for double integration in y direction
                    UnivariateFunction doubleFunctionY = new PolynomialFunction(doubleCoefficientsY);

                    // Perform double integration (definite)
                    double doubleDefiniteResult = doubleIntegration(doubleFunctionX, doubleLowerBoundX, doubleUpperBoundX, doubleFunctionY, doubleLowerBoundY, doubleUpperBoundY, true);
                    System.out.println("Double Integral (Definite) Result: " + doubleDefiniteResult);

                    break;
                case 4:
                    System.out.println("Enter the function f(x) for double integration (in terms of x):");
                    String doubleFunctionStringX_2 = scanner.nextLine();

                    System.out.println("Enter the function g(y) for double integration (in terms of y):");
                    String doubleFunctionStringY_2 = scanner.nextLine();

                    // Parse the string representing a polynomial expression into an array of coefficients for function f(x)
                    double[] doubleCoefficientsX_2 = parsePolynomialCoefficients(doubleFunctionStringX_2);

                    // Define the function for double integration in x direction
                    UnivariateFunction doubleFunctionX_2 = new PolynomialFunction(doubleCoefficientsX_2);

                    // Parse the string representing a polynomial expression into an array of coefficients for function g(y)
                    double[] doubleCoefficientsY_2 = parsePolynomialCoefficients(doubleFunctionStringY_2);

                    // Define the function for double integration in y direction
                    UnivariateFunction doubleFunctionY_2 = new PolynomialFunction(doubleCoefficientsY_2);

                    // Perform double integration (indefinite)
                    double doubleIndefiniteResult = doubleIntegration(doubleFunctionX_2, 0, 0, doubleFunctionY_2, 0, 0, false);
                    System.out.println("Double Integral (Indefinite) Result: " + doubleIndefiniteResult);

                    break;
                case 5:
                    System.out.println("Triple Integral (Definite) is not implemented yet.");
                    break;
                case 6:
                    System.out.println("Triple Integral (Indefinite) is not implemented yet.");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid numeric values.");
        } finally {
            scanner.close();
        }
    }

    // Method to parse a string representing a polynomial expression into an array of coefficients
    private static double[] parsePolynomialCoefficients(String expression) {
        // Handle the special case when the function is just "x"
        if (expression.equals("x")) {
            return new double[]{0.0, 1.0}; // Coefficients for the linear term 1*x
        }

        // Remove all whitespace characters from the expression
        expression = expression.replaceAll("\\s+", "");

        // Split the expression into individual terms using + or - as delimiters
        String[] terms = expression.split("(?=[-+])");

        double[] coefficients = new double[terms.length];

        for (int i = 0; i < terms.length; i++) {
            String term = terms[i];

            // If the term is empty (e.g., due to leading or trailing + or -), skip it
            if (term.isEmpty()) {
                continue;
            }

            // Extract the coefficient and exponent from the term
            String[] parts = term.split("x\\^?");
            double coefficient, exponent;

            if (parts.length == 0) {
                // If the term does not contain 'x', it represents a constant term
                coefficient = Double.parseDouble(term);
                exponent = 0.0;
            } else if (parts.length == 1) {
                // If the term contains 'x' but no exponent, it represents a linear term
                coefficient = parts[0].equals("-") ? -1.0 : Double.parseDouble(parts[0]);
                exponent = 1.0;
            } else {
                // If the term contains both 'x' and an exponent
                coefficient = parts[0].isEmpty() ? (parts[1].equals("-") ? -1.0 : 1.0) : Double.parseDouble(parts[0]);
                exponent = parts.length > 1 ? Double.parseDouble(parts[parts.length - 1]) : 1.0;
            }

            // Assign coefficient to the appropriate position in the array
            coefficients[i] = coefficient;
        }

        return coefficients;
    }

}
