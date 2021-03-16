package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestObject {

	private String testClass;
	private ArrayList<String[]> tests;

	private String inputPath, outputPath;

	public TestObject(String testCaseFilePath, String inputPath, String outputPath) {
		try {
			this.inputPath = inputPath;
			this.outputPath = outputPath;

			// Generate the data from the test case file
			BufferedReader br = new BufferedReader(new FileReader(testCaseFilePath));
			generateTestClass(br);
			generateTests(br);
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateTestClass(BufferedReader br) {
		try {
			// Get the class to run and test
			String line = br.readLine();
			if (line.equals("#CLASSNAME")) {
				testClass = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateTests(BufferedReader br) {
		try {
			tests = TestManager.generateTests(br, inputPath, testClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTestClass() {
		return testClass;
	}

	public ArrayList<String[]> getTests() {
		return tests;
	}

	public String getInputPath() {
		return inputPath;
	}

	public String getOutputPath() {
		return outputPath;
	}
}
