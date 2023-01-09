Code to Test Your Java / C++ Environment
========================================

This directory contains code that will verify that you can build command line and Swing graphical applications in Java as well as command line applications in C++.

## Build all applications

``make``

This will compile all C++ applications, and will invoke the ``ant`` Java build tool to compile all Java applications.

Pay attention to any error messages above the ==== divider! That's what you are checking with this build.

## Run each application

The ``make`` command will output instructions to run each test after the ==== divider. 

### TestJavaArgs

This program reads command line parameters and prints them to the terminal. So

``java TestJavaArgs one two three``

should print to the terminal:

one

two

three

### TestJavaFileIO

This program simply prints its own source code to the terminal to verify file reads.

### TestJavaIO

This program asks for the grade you'd like to receive in the class. 

If you respond A, B, C, D, F, or I, it will encourage you.

Otherwise, it will output an error message and ask again.

### TestJavaSwing

This program is the same as TestJavaIO, except it will display dialogs on the windowing system.
This is (I suspect) the program most likely to fail.

If you can't run Swing applications like this from Java, you will not be able to complete the second section of this class. Contact the Professor or TA if you have trouble with this one. Don't wait until you have an assignment deadline to discover that you can't develop Swing applications!

### test_cpp_io

As with the Java TestJavaIO, this C++ program will asks for the grade you'd like to receive in the class (from the console) and respond accordingly.

### test_cpp_file_io

As with the Java TestJavaFileIO, this C++ program will simply print its source code to the terminal.

