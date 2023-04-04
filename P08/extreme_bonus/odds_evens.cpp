#include <iostream>
#include <vector>

int main() {
    // A vector is like an ArrayList, but can contain primitives as well as objects
    std::vector<int> inputs;
    int i;
    
    // KNOW this idiom, which reads integers until end-of-file
    while(std::cin >> i) {
        // push_back is equivalent to Java ArrayList's add method
        inputs.push_back(i);
    }
    
    // As in Java, the size() method returns the number of elements
    std::cout << "Number of elements: " << inputs.size() << std::endl;
    
    // We could also use a for-each loop to print out the elements here
    // std::cout is similar to Java's System.out, and << is the streaming operator
    // std::endl is the same as '\n' but with a "flush" operation built-in
    std::cout << "Elements: ";
    for(i=0; i<inputs.size(); ++i)
        std::cout << inputs[i] << " ";
    std::cout << std::endl;
    
    // Probably easiest here to use a 3-term for, although toggling a bool
    //   in a for-each would also work
    std::cout << "Even indices: ";
    for(i=0; i<inputs.size(); i += 2) 
        std::cout << inputs[i] << " ";
    std::cout << "\nOdd  indices: ";
    for(i=1; i<inputs.size(); i += 2) 
        std::cout << inputs[i] << " ";
    std::cout << std::endl;
}
