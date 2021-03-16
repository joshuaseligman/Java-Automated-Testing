
import java.util.Scanner;

public class DistanceCalculator {

	public static void main(String[] args) {
		double x1, x2, y1, y2;

		Scanner s = new Scanner(System.in);

		String exitInput;

		int count = 0;

		// This is a simple program that asks the user for 2 points and then prints out
		// the distance between the points and the midpoint

		do {
			System.out.print("Enter x1: ");
			x1 = s.nextDouble();
			System.out.print("Enter y1: ");
			y1 = s.nextDouble();
			System.out.print("Enter x2: ");
			x2 = s.nextDouble();
			System.out.print("Enter y2: ");
			y2 = s.nextDouble();

			count++;
			System.out.println("\nHere is the information about Point #" + count + ": ");
			double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
			System.out.printf("Distance: %.3f\n", distance);

			double midx = (x1 + x2) / 2;
			// Should be y1 + y2, but error in calculation is present for testing purposes
			double midy = (y1 - y2) / 2;
			System.out.printf("Midpoint: (%.3f, %.3f)\n", midx, midy);

			System.out.print("\nWould you like to enter more values (Y/N)? ");
			exitInput = s.next();

		} while (exitInput.toUpperCase().charAt(0) == 'Y');
	}
}
