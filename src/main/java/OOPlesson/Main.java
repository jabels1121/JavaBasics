package OOPlesson;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Dog lab = new Dog();
        lab.setName("Mikey");
        lab.setBreed("Lab");

        Dog sheppard = new Dog();
        sheppard.setName("Tim");
        sheppard.setBreed("Sheppard");

        Dog mops = new Dog();
        mops.setName("Elly");
        mops.setBreed("Mops");

        List<Dog> dogs = new ArrayList<>();
        dogs.add(mops);
        dogs.add(sheppard);
        dogs.add(lab);


        for (Dog i : dogs) {
            i.bark();
        }

        Size[] test = Size.values();

        for (Size i : test) {
            System.out.println(i + " abbreviation is " + i.getAbbreviation());
        }
    }

}
