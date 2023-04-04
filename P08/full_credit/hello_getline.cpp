#include <iostream>

int main() {
   // In C++, we use the std::string class NOT char*
   // This is very similar to Java's String class, except mutable
   std::string name;
   
   // std::cout << is similar to Java's System.out.print
   //   or C's printf (but without the formatting options)
   std::cout << "What's your name? ";
   
   // Similar to C's getline function, but using an object
   //   such as std::cin as the incoming stream, and a std::string
   //   object as the recipient of a full newline-terminated
   //   line of text.
   std::getline(std::cin, name);
   
   // As with other operators like +, << (and >>) can be chained!
   std::cout << "Hello, " << name << "!" << std::endl;
}
