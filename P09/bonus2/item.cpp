#include "item.h"

#include <stdexcept>

Item::Item(std::string name, int price) : _name{name}, _price{price} {
    if(price < 0) throw std::runtime_error{"Negative price" + price};
}
int Item::price() {
    return _price;
}
std::ostream& operator<<(std::ostream& os, const Item& item) {
    return os << item._name << " ($" << std::to_string(item._price/100) 
                       << "."        << std::to_string(item._price%100)
                       << ")";
}
std::istream& operator>>(std::istream& is,       Item& item) {
    std::getline(is, item._name);
    is >> item._price; is.ignore();
    return is;
}

