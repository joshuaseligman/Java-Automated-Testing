# Java-Automated-Testing

This project was made to enhance my software development and testing skills in my Software Development I class.

During the first few weeks of the semester, I realized that all of my projects in Software Development I had numerous edge cases and small conditions that were difficult to check by hand, and running the program many times and manually typing in a lot of input values was a tedious process. This problem inspired me to create my own testing application that reads a single file with all of my desired inputs and expected outputs, runs the program and then compares the actual output with the expected output. I wanted this system to be able to be used for any programming assignment in the class, so creating an application with modularity was extremely important to me. I am aware that there are already tools available that can do this entire process for me. However, due to the small scale of the programs that I would be running and my love for problem-solving, I wanted to challenge myself by building the system on my own while becoming familiar with the JUnit library for testing in Java.

I chose to use the JUnit library for the application because I wanted to learn more about testing and some of the technologies used in the field. After learning the basics of the library, I dove into the code and began to apply my newly gained skills to my project. The main problem that I dealt with throughout my time working on the project was the constant battle between the flexibility that I wanted to incorporate into the application and the simplistic and rigid nature of JUnit. I quickly found out that JUnit does not play well when the number of tests and the size of each test is unknown, and my personal requirements for the project were to have both of these variables unknown so the application would work with any project that I have in Software Development I. I then found a solution to the problem by using only one JUnit test and keeping track of my own smaller tests inside of the JUnit test and then calling a failure if there are any differences between the actual and expected output. JUnit's control loop with `@BeforeAll`, `@Test`, and `@AfterAll` was very useful in organizing the flow of the code and clearly defining what actions were to come first, second, and last.

Overall, my experience with creating this testing environment was really enjoyable because I was able to learn more about the software testing field, which I had minimal knowledge about, while also building an application that will encourage me to develop high-quality unit tests on my own. I am looking forward to using this application for my benefit in my Software Development I class as I can now easily test all of the edge cases in the programs before submitting them for grading.

Below, I will explain how to write your own unit tests for the sample DistanceCalculator application using my automated testing application.

The repository contains the entire Eclipse project, so Eclipse is required to run my testing application. If you do not have Eclipse, you can download it here: https://www.eclipse.org

Once you have Eclipse installed, download the repository and open it as a Java project.

Navigate to the `src` folder and open `DistanceCalculator.java`. This is the sample class that I will use in this tutorial. However, it can be any other Java file with the main method that uses the `Scanner` class for input. Additionally, this file must remain in the `default package`, which means that it does not belong in any package.

Next, navigate to the `src/test/res` folder inside of the project and open `TestCase.txt`. This is the file in which all of the tests will be written in.

The beginning of the `TestCase.txt` file will always be defining the class that contains the main method. To do this, the first line of the file will be `#CLASSNAME`, and it will be followed on the next line by the actual name of the class (in this case, it is `DistanceCalculator`.

The next important part of the file is the `#TEST` line. `#TEST` tells the program that you are about to define a test. Tests are flexible in that you can break up your input and output into chunks that make sense together within the context of your program. Although you can have just one big `#TEST`, I believe that using multiple instances of `#TEST` will make the `TestCase.txt` file and results easier to understand.

Every `#TEST` has three main components: `#INPUT`, `#PREFIX`, and `#OUTPUT`.

First, `#INPUT` defines the input that relates to the `#TEST`. After declaring the beginning of the input, you are now able to write the input for the `#TEST`. In the first example, I want to input the coordinates of `(3.1, 8.5)` and `(2.4, 9.6)` and tell the computer that I do want to input more coordinates after the distance and midpoint are calculated. When all of the input is written, close the `#INPUT` with a `#ENDINPUT` tag. Since you can have multiple instances of `#TEST`, each `#INPUT` will be combined to form one large input when running the program. I strongly recommend that you look at the `In.txt` file to see how this is done in a real situation.

Next, after determining the input, the `#PREFIX` has to be defined. `#PREFIX` represents the line of output that immediately precedes the output that will be compared against. In the example, I declare the first `#PREFIX` to be `Here is the information about Point #1:` with the expectation that this line will be right before the output.

Lastly, the expected output has to be defined for testing purposes. Defining the output is identical to defining input as it begins with a `#OUTPUT` tag and ends with a `#ENDOUTPUT` tag. In the first example, I expect the output to be two lines in length, and each line contains the following content: `Distance: 1.304` and `Midpoint: (2.750, 9.050)`, respectively.

As previously mentioned and as shown in the example, you may repeat these steps and add more `#TEST` tags to your desire to ensure all of your cases are covered.

The final step of the process is to open the `Test.java` file located in the `test` package. Running this file will conduct all of the tests automatically and display the results in the JUnit window as well as the standard console. In the example, there is a miscalculation of the midpoint, which causes all three `#TEST` cases to fail for their midpoint comparison. Each case is successful with the distance calculation.

And that's it! I hope this tutorial was useful in understanding how my application works and I hope that you may find this automated testing environment useful for your smaller Java applications like I do for my academic purposes.
