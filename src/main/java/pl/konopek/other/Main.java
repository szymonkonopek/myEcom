package pl.konopek.other;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Szymon", "Amelia", "Karolina");
        Greeter greeter = new Greeter();
        // greeter.great("Szymon");



        greeter.greetLadies(names);

        names.stream().
                filter(name -> name.endsWith("a")).
                filter(name -> name.startsWith("K")).
                forEach(greeter::great);
    }
}