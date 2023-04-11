#ifndef __VENDING_MACHINE
#define __VENDING_MACHINE

#include "item.h"
#include <vector>

class Vending_machine {
  public:
    void add(std::string name, int price);
    std::string menu();
    void buy(int index);
  private:
    std::vector<Item> items;
};

#endif
