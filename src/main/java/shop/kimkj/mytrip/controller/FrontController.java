package shop.kimkj.mytrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    @GetMapping("/")
    public String getPageIndex() {
        return "index";
    }

    @GetMapping("/write")
    public String getPageWrite() {
        return "templates/write";
    }
}
