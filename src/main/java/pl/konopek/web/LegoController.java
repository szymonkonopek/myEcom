package pl.konopek.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LegoController {

    @GetMapping("/lego")
    public String lego() {
        return "lego";
    }

}