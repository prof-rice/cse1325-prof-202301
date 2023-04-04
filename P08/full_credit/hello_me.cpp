#include <iostream>

// In C++ as in C, main is a function NOT a method
// Unlike Java, we may simply ignore the arguments
// Also unlike Java, we have a return type
//   (which may be ignored)
int main() {

   // std::cout << is similar to Java's System.out.print
   //   or C's printf (but without the formatting options)
   // std::endl is the same as '\n' but with a "flush" operation built-in
   std::cout << "Hello, Professor Rice!" << std::endl;
}
