package pl.konopek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jakub", "Michal", "Kasia", "Maria");

        Greeter greeter = new Greeter();
        greeter.greet("Kuba"); // -> Helllo Kuba

        List<String> ladies = new ArrayList<String>();
        for (String name : names) {
            if (name.endsWith("a")) {
                ladies.add(name);
            }
        }

        for (String ladyName: ladies) {
            greeter.greet(ladyName);
        }

        names.stream()
                .filter(name -> name.endsWith("a")) // lambda name: name[-1] == "a"
                .filter(name -> name.startsWith("K"))
                .map(String::toUpperCase)
                .forEach(greeter::greet);




        //Greet all ladies
        // Hello Kasia
        // Hello Maria
    }
}
