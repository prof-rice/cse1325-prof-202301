#include "light.h"

std::string to_string(Light light) {
    if(light == Light::FULL_SUN) return "full sun";
    if(light == Light::PART_SHADE) return "part shade";
    if(light == Light::SHADE) return "shade";
    return "UNKNOWN";
}

std::ostream& operator<<(std::ostream& ost, const Light& light) {
    return ost << to_string(light);
}

