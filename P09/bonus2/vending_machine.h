#ifndef __VENDING_MACHINE
#define __VENDING_MACHINE

#include "item.h"
#include <vector>
#include <iostream>

class Vending_machine {
  public:
    Vending_machine();
    Vending_machine(std::istream& ist);
    void add(std::string name, int price);
    void buy(int index);
    friend std::ostream& operator<<(std::ostream& os, const Vending_machine& vm);
    // std::string menu(); // No longer required!
  private:
    std::vector<Item> items;
};

#endif
