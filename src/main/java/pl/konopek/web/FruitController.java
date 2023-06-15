package pl.konopek.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FruitController {

    @GetMapping("/fruits")
    public String fruit() {
        return "fruits";
    }

}