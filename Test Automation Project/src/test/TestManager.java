package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestManager {

	public static ArrayList<String[]> generateTests(BufferedReader br, String inputPath, String className)
			throws IOException {
		// Begin making the string to write to the file for input
		StringBuilder inFile = new StringBuilder();
		inFile.append(className.length() + 1 + " " + className + "\n");

		// Each string array will contain 2 elements. The prefix to look for in the
		// output to begin testing, and the actual expected output that follows the
		// prefix beginning on the next line of output.
		ArrayList<String[]> tests = new ArrayList<String[]>();

		String line;
		while ((line = br.readLine()) != null) {
			if (line.equals("#TEST")) {
				// Get the input, prefix, and output of the test
				String input = getInput(br);
				String prefix = getPrefix(br);
				String output = getOutput(br);

				inFile.append(input);
				tests.add(new String[] { prefix, output });
			}
		}

		writeInput(inputPath, inFile.toString());
		return tests;
	}

	private static String getInput(BufferedReader br) throws IOException {
		if (br.readLine().equals("#INPUT")) {
			StringBuilder input = new StringBuilder();
			String line;
			//Collect input until it reaches a line that reads #ENDINPUT
			while (!(line = br.readLine()).equals("#ENDINPUT")) {
				input.append(line + "\n");
			}
			return input.toString();
		}
		return null;
	}

	private static String getPrefix(BufferedReader br) throws IOException {
		// Get the prefix that precedes the expected output
		if (br.readLine().equals("#PREFIX")) {
			return br.readLine();
		}
		return null;
	}

	private static String getOutput(BufferedReader br) throws IOException {
		if (br.readLine().equals("#OUTPUT")) {
			StringBuilder output = new StringBuilder();
			String line;
			//Get the expected output
			while (!(line = br.readLine()).equals("#ENDOUTPUT")) {
				output.append(line + "\n");
			}
			return output.toString();
		}
		return null;
	}

	public static void writeInput(String path, String content) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		bw.write(content);
		bw.close();
	}

	public static String readOutputFile(String path) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			output.append(line + "\n");
		}
		return output.toString();
	}
}
