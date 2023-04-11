#ifndef __ITEM
#define __ITEM

#include <iostream>

class Item {
  public:
    Item(std::string _name, int _price);
    int price();
    friend std::ostream& operator<<(std::ostream& os, const Item& item);
    friend std::istream& operator>>(std::istream& is,       Item& item);
    // std::string to_string(); // No longer needed!
  private:
    std::string _name;
    int _price;
};

#endif
