#include "item.h"

#include <stdexcept>

Item::Item(std::string name, int price) : _name{name}, _price{price} {
    if(price < 0) throw std::runtime_error{"Negative price" + price};
}
/*int Item::price() {
    return _price;
}*/
std::string Item::to_string() {
    return _name + " ($" + std::to_string(_price/100) 
                   + "." + std::to_string(_price%100)
                   + ")";
}

