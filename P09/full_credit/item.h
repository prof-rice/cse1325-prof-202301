#ifndef __ITEM
#define __ITEM

#include <string>

class Item {
  public:
    Item(std::string _name, int _price);
//    int price();
    std::string to_string();
  private:
    std::string _name;
    int _price;
};

#endif
