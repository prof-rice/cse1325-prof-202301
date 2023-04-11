#include "vending_machine.h"

#include <iostream>
#include <fstream>

int main() {
    std::ifstream ist{"products.txt"};
    Vending_machine vm{ist};
    int index = 0;
    while(std::cin && index >= 0) {
        std::cout << vm << "\n\nProduct? ";
        std::cin >> index; std::cin.ignore();
        if(std::cin && index >= 0) vm.buy(index);
    }
}
