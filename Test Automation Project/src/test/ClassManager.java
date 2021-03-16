package test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassManager {

	public static Class getFileClass(InputStream in) {
		int nameLength;
		try {
			// Read the first 2 characters of the input file to determine the length of the
			// line to get the name of the class.
			nameLength = Integer.valueOf(new String(in.readNBytes(2)));
			String className = new String(in.readNBytes(nameLength)).substring(1);
			Class fileClass = Class.forName(className);
			return fileClass;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Class getClass(String className) {
		try {
			//Get the class based on its name
			Class fileClass = Class.forName(className);
			return fileClass;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void invokeMainMethod(Class fileClass) {
		try {
			//Invoke the main method of the appropriate class
			Method mainMethod = fileClass.getDeclaredMethod("main", new Class[] {String[].class});
			mainMethod.invoke(null, (Object) (new String[] {}));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
