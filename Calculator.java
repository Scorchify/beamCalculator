import java.util.Scanner;
import java.lang.Math;

public class Calculator { 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter what type of beam you are using: (I, SQUARE) or type 'back' to exit:");
            String beamType = input.nextLine();
            if (beamType.equalsIgnoreCase("back")) {
                break;
            }
            if (beamType.equalsIgnoreCase("I")) {
                double height = getInput(input, "Enter the total height of the beam (in inches): ");
                if (height == -1) continue;
                double web_x = getInput(input, "Enter the Web X dimension (in inches): ");
                if (web_x == -1) continue;
                double web_y = getInput(input, "Enter the Web Y dimension (in inches): ");
                if (web_y == -1) continue;
                double flange_x = getInput(input, "Enter the Flange X dimension (in inches): ");
                if (flange_x == -1) continue;
                double flange_y = getInput(input, "Enter the Flange Y dimension (in inches): ");
                if (flange_y == -1) continue;
                double depth = getInput(input, "Enter the depth of your I beam (in inches): ");
                if (depth == -1) continue;
                double density = getInput(input, "Last input, please provide the density of the beam (in g/in³): ");
                if (density == -1) continue;
                double mass = density * (depth * web_x * web_y + 2 * (flange_x * flange_y * depth));
                iBeamMOI(height, web_x, web_y, flange_x, flange_y, depth, density, mass);

            } else if (beamType.equalsIgnoreCase("SQUARE")) {
                double width = getInput(input, "Enter the overall width dimension (in inches): ");
                if (width == -1) continue;
                double height = getInput(input, "Enter the overall height dimension (in inches): ");
                if (height == -1) continue;
                double depth = getInput(input, "Enter the depth of your square beam (in inches): ");
                if (depth == -1) continue;
                double density = getInput(input, "Last input, please provide the density of the beam (in g/in³): ");
                if (density == -1) continue;
                double mass = density * (width * height * depth);
                squareBeamMOI(width, height, depth, density, mass);
            }
        }
        input.close();
    }

    public static double getInput(Scanner input, String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = input.nextLine();
            if (userInput.equalsIgnoreCase("back")) {
                return -1;
            }
            try {
                return Double.parseDouble(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or type 'back' to go back.");
            }
        }
    }

    public static void squareBeamMOI(double width, double height, double depth, double density, double mass) { 
        double momentOfInertia = (1.0/12) * (width * Math.pow(height, 3));
        System.out.printf("The moment of inertia of your square beam is: %.3f in^4%n", momentOfInertia);
        System.out.printf("The mass of your square beam is: %.3f grams%n", mass);
        System.out.printf("The strength to weight ratio of your beam is: %.3f%n", momentOfInertia / mass);
    }
    
    public static void iBeamMOI(double height, double web_x, double web_y, double flange_x, double flange_y, double depth, double density, double mass) { 
        double momentOfInertia = ((flange_x * Math.pow(height, 3))/12) - ((flange_x-web_x)*Math.pow(height-2*flange_y, 3)/12);
        System.out.printf("The moment of inertia of your I beam is: %.3f in^4%n", momentOfInertia);
        System.out.printf("The mass of your I beam is: %.3f grams%n", mass);
        System.out.printf("The strength to weight ratio of your beam is: %.3f%n", momentOfInertia / mass);
    }
}