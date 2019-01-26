package OOPlesson;

public class Dog {

    public static final int PAWS = 4, TAIL = 1;
    private String name, breed;
    private Size size = Size.UNDEFINED;
    private static int dogsCounter;

    public Dog() {
        dogsCounter++;
        System.out.println("Dog's counter is: " + dogsCounter);
    }

    public static int getDogsCounter() {
        return dogsCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void bark() {
        switch (size) {
            case VERY_BIG:
            case BIG:
                System.out.println("Woof! Woof!");
                break;
            case AVERAGE:
                System.out.println("Raf! Raf!");
                break;
            case SMALL:
            case VERY_SMALL:
                System.out.println("Tyaf! Tyaf!");
            default:
                System.out.println("Dog`s size is undefined.");
        }
    }

    public void bite() {
        if (dogsCounter > 2) {
            System.out.println(this.name + " doing bite!");
        } else {
            bark();
        }
    }

}
