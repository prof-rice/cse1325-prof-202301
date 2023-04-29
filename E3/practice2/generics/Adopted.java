import java.util.ArrayList;

class Animal { }
class Client { }

class Pair {
    public Pair(Animal animal, Client client) {
        this.animal = animal;
        this.client = client;
    }
    public Animal getAnimal() {return animal;}
    public Client getClient() {return client;}
    private Animal animal;
    private Client client;
}

public class Adopted {
    private ArrayList<Pair> adoptions = new ArrayList<>();
    public void adopt(Animal animal, Client client) {
        adoptions.add(new Pair(animal, client));
    }
    @Override
    public String toString() {
        String s = "";
        for(Pair p : adoptions) 
            s += p.getClient() + "->" + p.getAnimal();
        return s;
    }
}
