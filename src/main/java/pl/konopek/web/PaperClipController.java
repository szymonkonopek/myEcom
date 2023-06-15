package pl.konopek.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PaperClipController {

    @GetMapping("/paper-clip")
    public String paperclip() {
        return "paper-clip";
    }

}