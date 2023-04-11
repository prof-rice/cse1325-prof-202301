#include "vending_machine.h"

#include <iostream>

Vending_machine::Vending_machine() { }
Vending_machine::Vending_machine(std::istream& ist) {
    std::string name;
    int price;
    while(ist) {
        std::getline(ist, name);
        ist >> price; ist.ignore();
        if(ist) {
            items.push_back(Item{name, price});
        }
    }
}

void Vending_machine::add(std::string name, int price) {
    items.push_back(Item{name, price});
}
void Vending_machine::buy(int index) {
    std::cout << "#### Buying " << items[index] << "\n";
}

std::ostream& operator<<(std::ostream& os, const Vending_machine& vm) {
    os << "\n"
       << "======================\n"
       << "Welcome to UTA Vending\n"
       << "======================\n";
    for(int i=0; i<vm.items.size(); ++i)
        os << i << ") " << vm.items[i] << '\n';
    return os;
}
