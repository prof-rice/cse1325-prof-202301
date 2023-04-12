#include "dvector.h"
#include <iostream>

int main() {
    std::string name;
    int age;
    DVector<decltype(name), decltype(age)> ages;

    while(true) {
        std::cout << "Name? "; 
        std::getline(std::cin, name);
        if(name.empty()) break;

        std::cout << "Age? ";
        std::cin >> age; std::cin.ignore(32767, '\n');

        ages.push_back(name, age);
    }

    for(int i=ages.size()-1; i>=0; --i) {
        std::cout << ages.at1(i) << " is "
                  << ages.at2(i) << " years old" << std::endl;
    }
}
