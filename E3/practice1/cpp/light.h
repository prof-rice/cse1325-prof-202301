#ifndef __LIGHT_H
#define __LIGHT_H

#include <iostream>

enum class Light {FULL_SUN, PART_SHADE, SHADE};
std::string to_string(Light light);
std::ostream& operator<<(std::ostream& ost, const Light& light);

#endif
