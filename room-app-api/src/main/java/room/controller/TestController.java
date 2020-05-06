package room.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public Object hello() {
        List<String> list = new ArrayList<>();
        list.add("qqq");
        list.add("www");
        list.add("eee");
        return list;
    }
}
