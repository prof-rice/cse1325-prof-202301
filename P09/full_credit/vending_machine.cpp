#include "vending_machine.h"

#include <iostream>


void Vending_machine::add(std::string name, int price) {
    items.push_back(Item{name, price});
}   
std::string Vending_machine::menu() {
    std::string menu = std::string{"\n"}
      + "======================\n"
      + "Welcome to UTA Vending\n"
      + "======================\n";
    for(int i=0; i<items.size(); ++i)
        menu += std::to_string(i) + ") " + items[i].to_string() + '\n';
    return menu;
}
void Vending_machine::buy(int index) {
    std::cout << "#### Buying " + items[index].to_string() + "\n";
}
