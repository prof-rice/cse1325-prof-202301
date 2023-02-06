public class MyPets {
    public static void main(String[] args) {
        Pet[] pets = new Pet[4];
        
        pets[0] = new Pet("Fido", 3.2, Type.DOG);
        pets[1] = new Pet("Garfield", 0.7, Type.CAT);
        pets[2] = new Pet("Bugs", 4.3, Type.RABBIT);
        pets[3] = new Pet("Godzilla", 1.9, Type.IGUANA);
        
        for(Pet p : pets)
            System.out.println(p);
    }
}

