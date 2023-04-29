import java.util.HashMap;
import java.util.Objects;
import java.util.Iterator;

enum PlanetType {rocky, gaseous, icy};
class Planet {
    public Planet(String name, double radius) {
        this.name = name;
        this.radius = radius;
    }
    @Override
    public String toString() {
        return name + " (radius " + radius + " km)";
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, radius);
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(!o.getClass().equals(this.getClass())) return false;
        Planet p = (Planet) o;
        return name.equals(p.name) &&
               radius == p.radius;
    }
    private String name;
    private Double radius;
}

public class SolarSystem {
    private static HashMap<Planet, PlanetType> planets = new HashMap<>();
    public static void main(String[] args) {
        planets.put(new Planet("Mercury", 2440), PlanetType.rocky);
        planets.put(new Planet("Venus", 6052), PlanetType.rocky);
        planets.put(new Planet("Earth", 6371), PlanetType.rocky);
        planets.put(new Planet("Mars", 3390), PlanetType.rocky);
        planets.put(new Planet("Jupiter", 69911), PlanetType.gaseous);
        planets.put(new Planet("Saturn", 58232), PlanetType.gaseous);
        planets.put(new Planet("Uranus", 25362), PlanetType.icy);
        planets.put(new Planet("Neptune", 24622), PlanetType.icy);

        Iterator<Planet> p = planets.keySet().iterator();
        while(p.hasNext()) {
            Planet planet = p.next();
            System.out.println(planet + " is " + planets.get(planet));
        }
    }
}


