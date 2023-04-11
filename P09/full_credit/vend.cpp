#include "vending_machine.h"

#include <iostream>

int main() {
    Vending_machine vm{};
    vm.add("Peanut butter crackers", 169);
    vm.add("Oreos", 189);
    std::cout << vm.menu() << std::endl;
    vm.buy(1);
}
