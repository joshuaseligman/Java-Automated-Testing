package test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Test {

	public static TestObject testObject;

	public static ArrayList<String> failures, successes;
	
	private static PrintStream console = System.out;

	@org.junit.jupiter.api.BeforeAll
	public static void setUp() {
		try {

			// Create the test object, passing in the paths of the required files
			testObject = new TestObject("./src/test/res/TestCase.txt", "./src/test/res/In.txt",
					"./src/test/res/Out.txt");

			// Change the input and output streams to use the files
			InputStream in = new FileInputStream(testObject.getInputPath());
			System.setIn(in);

			PrintStream out = new PrintStream(testObject.getOutputPath());
			System.setOut(out);

			// Run the main method of the class listed in the TestCase.txt file
			Class className = ClassManager.getFileClass(in);
			ClassManager.invokeMainMethod(className);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@org.junit.jupiter.api.DisplayName("Program Test")
	@org.junit.jupiter.api.Test
	public void test() {
		failures = new ArrayList<String>();
		successes = new ArrayList<String>();
		try {
			// Read the output and convert to string array
			String output = TestManager.readOutputFile(testObject.getOutputPath());
			String[] outputLines = output.split("\n");

			int testCase = 1;
			for (String[] test : testObject.getTests()) {
				String prefix = test[0];
				String[] testLines = test[1].split("\n");

				// Get line to begin testing
				int outputLine;
				for (outputLine = 0; outputLine < outputLines.length; outputLine++) {
					if (outputLines[outputLine].contains(prefix)) {
						outputLine++;
						break;
					}
				}

				// Iterate through the test case expected and compare with the actual
				for (int testLine = 0; testLine < testLines.length
						&& outputLine < outputLines.length; testLine++, outputLine++) {
					// Add the error if lines do not match
					if (!outputLines[outputLine].equals(testLines[testLine])) {
						failures.add("Failed test " + testCase + ", line " + (testLine + 1) + ". Expected: "
								+ testLines[testLine] + "  Received: " + outputLines[outputLine]);
					} else {
						successes.add("Passed test " + testCase + ", line " + (testLine + 1) + ". Correct output: "
								+ outputLines[outputLine]);
					}
				}

				testCase++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Throw the JUnit fail
		if (failures.size() > 0) {
			fail(failures.toString());
		}
	}

	@org.junit.jupiter.api.AfterAll
	public static void displayResults() {
		System.setOut(console);

		// Print out failures
		System.err.print("Failures: ");
		if (failures.size() == 0) {
			System.err.println("None");
		} else {
			System.out.println();
			for (String failure : failures) {
				System.err.println(failure);
			}
		}

		System.out.println();

		// Print out successes
		System.out.print("Successes: ");
		if (successes.size() == 0) {
			System.out.println("None");
		} else {
			System.out.println();
			for (String success : successes) {
				System.out.println(success);
			}
		}
	}
}