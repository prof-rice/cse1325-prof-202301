#ifndef __GARDEN_H
#define __GARDEN_H

#include "plant.h"
#include <vector>

class Garden {
  public:
    Garden(Light light);
    void add_plant(Plant plant);
    std::string to_string();  
  private:
    std::vector<Plant> _plants;
    Light _light;
};

#endif
