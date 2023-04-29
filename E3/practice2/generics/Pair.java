import java.util.ArrayList;

public class Pair<T, U> {
    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }
    public T getFirst() {return t;}
    public U getSecond() {return u;}
    private T t;
    private U u;
}

class Animal { }
class Client { }

class Adopted {
    private ArrayList<Pair> adoptions = new ArrayList<>();
    public void adopt(Animal animal, Client client) {
        adoptions.add(new Pair<Animal, Client>(animal, client));
    }
    @Override
    public String toString() {
        String s = "";
        for(Pair p : adoptions) 
            s += p.getFirst() + "->" + p.getSecond();
        return s;
    }
}
