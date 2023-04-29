#include "garden.h"

int main() {
    Garden garden{Light::PART_SHADE};
    garden.add_plant(Plant{"Rose", Light::FULL_SUN});
    garden.add_plant(Plant{"Periwinkle", Light::SHADE});
    std::cout << garden.to_string() << std::endl;
}
    
