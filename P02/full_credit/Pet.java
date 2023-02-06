public class Pet {
    public Pet(String name, double age, Type type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }
    @Override
    public String toString() {
        return name + " is a " + type + " age " + age;
    }
    private String name;
    private double age;
    private Type type;
}

