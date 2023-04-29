import java.util.ArrayList;

abstract class Animal {
    private String name;
    public Animal(String name) {this.name = name;}
    public abstract void speak();
    @Override public String toString() {return name;}
}

class Cat extends Animal {
    public Cat(String name) {super(name);}
    @Override public void speak() {System.out.println("Meow");}
}

class Dog extends Animal {
    public Dog(String name) {super(name);}
    @Override public void speak() {System.out.println("Woof");}
}

public class Storm {
    ArrayList<Animal> animals = new ArrayList<>();
    public Storm() {
        animals.add(new Cat("Mittens"));
        animals.add(new Dog("Spot"));
    }
    public void raining() {
        for(Animal a: animals) a.speak();
    }
    public static void main(String[] args) {
        Storm storm = new Storm();
        storm.raining();
    }
}
