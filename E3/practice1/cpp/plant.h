#ifndef __PLANT_H
#define __PLANT_H

#include "light.h"

class Plant {
  public:
    Plant(std::string name, Light light);
    std::string name();
    Light light();
  private:
    std::string _name;
    Light _light;
};

#endif
