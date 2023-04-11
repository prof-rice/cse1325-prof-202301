#include "vending_machine.h"

#include <iostream>
#include <fstream>

int main() {
    std::ifstream ifs{"products.txt"};
    Vending_machine vm{ifs};
    //vm.add("Peanut butter crackers", 169);
    //vm.add("Oreos", 189);
    std::cout << vm << std::endl;
    vm.buy(1);
}
