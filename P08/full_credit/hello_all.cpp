 #include <iostream>

 int main() {
   // In C++, we use the std::string class NOT char*
   // This is very similar to Java's String class, except mutable
   std::string name;
   
   // std::cout << is similar to Java's System.out.print
   //   or C's printf (but without the formatting options)
   std::cout << "What's your name? ";
   
   // std::cin is like Java's System.in, but we use the >> operator
   //   instead of a Scanner. The type of the right-hand variable
   //   determines how the incoming text stream is processed,
   //   whitespace-separated as with Scanner's next, nextInt, etc.
   std::cin >> name; // or std::getline(std::cin, name);
   
   // As with other operators like +, << (and >>) can be chained!
   std::cout << "Hello, " << name << "!" << std::endl;
 }
