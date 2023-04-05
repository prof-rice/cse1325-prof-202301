#include <iostream>

// Arguments are handled exactly as in C
int main(int argc, char* argv[]) {

    // std::string has a constructor that accepts a char* 
    std::string previous{argv[1]};
    
    // std::cout << is similar to Java's System.out.print
    std::cout << previous << " ";
    
    // As in C, arg[0] is the name of the program, so we're starting
    //   with the second argument in this loop (having already printed
    //   the first)
    for(int i=2; i<argc; ++i) {
        std::string current{argv[i]};
        if(current != previous) 
           std::cout << current << " ";
        previous = current;
    }
    
    // std::endl is the same as '\n' but with a "flush" operation built-in
    std::cout << std::endl;
}
        
