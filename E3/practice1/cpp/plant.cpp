#include "plant.h"

Plant::Plant(std::string name, Light light) : _name{name}, _light{light} { }
std::string Plant::name() {return _name;}
Light Plant::light() {return _light;}

