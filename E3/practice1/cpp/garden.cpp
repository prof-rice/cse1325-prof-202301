#include "garden.h"

Garden::Garden(Light light) : _light{light} { }
void Garden::add_plant(Plant plant) {_plants.push_back(plant);}
std::string Garden::to_string() {
    std::string s;
    for(Plant p : _plants) {
        s += p.name() + " (" + ::to_string(p.light()) + "), ";
    }
    return s;
}

