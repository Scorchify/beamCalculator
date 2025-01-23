import java.util.Scanner;
import java.lang.Math;

public class Calculator { 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter what type of beam you are using: (I, SQUARE)");
        String beamType = input.nextLine();
        if (beamType.equals("I")) {
            System.out.println("Enter the total height of the beam: ");
            double height = input.nextDouble();
            System.out.println("Enter the Web X dimension: ");
            double web_x = input.nextDouble();
            System.out.println("Enter the Web Y dimension: ");
            double web_y = input.nextDouble();
            System.out.println("Enter the Flange X dimension: ");
            double flange_x = input.nextDouble();
            System.out.println("Enter the Flange Y dimension: ");
            double flange_y = input.nextDouble();
            System.out.println("Enter the depth of your I beam: ");
            double depth = input.nextDouble();
            System.out.println("Last input, please provide the density of the beam: ");
            double density = input.nextDouble();
            input.close();
            iBeamMOI(height, web_x, web_y, flange_x, flange_y, depth, density);

            //formula for moment of inertia of an I beam 
        } else if (beamType.equals("SQUARE")) {
            System.out.println("Enter the overall width dimension: ");
            double width = input.nextDouble();
            System.out.println("Enter the overall height dimension: ");
            double height = input.nextDouble();
            System.out.println("Enter the depth of your square beam: ");
            double depth = input.nextDouble();
            System.out.println("Last input, please provide the density of the beam: ");
            double density = input.nextDouble();
            input.close();
            squareBeamMOI(width, height, depth, density);
        }
    }

    public static void squareBeamMOI(double width, double height, double depth, double density) { 
        //formula for moment of inertia of a square beam = (1/12) * (bh^3)
        //density = mass/volume or mass = density * volume 
        double momentOfInertia = (1.0/12) * (width * Math.pow(height, 3));
        double mass = density * width * height * depth;
        System.out.println("The moment of inertia of your square beam is: " + Math.round(momentOfInertia));
        System.out.println("The mass of your square beam is: " + Math.round(mass));
        System.out.println("The strength to weight ratio of your beam is " + Math.round(momentOfInertia/mass)); 
    }
    
    public static void iBeamMOI(double height, double web_x, double web_y, double flange_x, double flange_y, double depth, double density) { 
        double momentOfInertia = ((flange_x * Math.pow(height, 3))/12) - ((flange_x-web_x)*Math.pow(height-2*flange_y, 3)/12);
        double mass = density * (web_x * web_y * depth + 2*(flange_x * flange_y * depth));
        System.out.println("The moment of inertia of your I beam is: " + Math.round(momentOfInertia));
        System.out.println("The mass of your I beam is: " + Math.round(mass));
        System.out.println("The strength to weight ratio of your beam is " + Math.round(momentOfInertia/mass)); 
    }
}