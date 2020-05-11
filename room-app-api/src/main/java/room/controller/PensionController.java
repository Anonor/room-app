package room.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import room.service.PensionService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pension")
public class PensionController {

    @Autowired
    private PensionService pensionService;

    //商家增加民宿
    @GetMapping("/add")
    public int add(HttpServletRequest request){
        System.out.println(request.getSession().getAttribute("id"));
        return 0;
    }
}
