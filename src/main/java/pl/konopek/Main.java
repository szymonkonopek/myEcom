package pl.konopek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jakub", "Michal",
                "Agnieszka", "Ola", "Kasia");
        Greeter greeter = new Greeter();
        greeter.greet("Jakub"); // -> Hello Jakub

        List<String> ladies = new ArrayList<String>();
        for (String name : names) {
            if (name.endsWith("a")) {
                ladies.add(name);
            }
        }
        for (String ladyName: ladies) {
            greeter.greet(ladyName);
        }
        System.out.println("------------------------------------");
        names.stream()
                .filter(name -> name.endsWith("a")) // python way lambda name: name[-1] == "a"
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .forEach(greeter::greet);

    }
}
