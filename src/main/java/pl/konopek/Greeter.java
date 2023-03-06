package pl.konopek;

import java.util.List;

public class Greeter {
    public void great(String name) {
        System.out.println("Hello " + name);
    }

    public void greetLadies(List<String> users){
        for (String user : users){
            if (user.charAt(user.length() - 1) == 'a'){
                great(user);
            }
        }
    }
}