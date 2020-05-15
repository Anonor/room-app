package room.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import room.common.utils.GetLngAndLatUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public Object hello() {
        System.out.println("hellos");
        List<String> list = new ArrayList<>();
        list.add("qqq");
        list.add("www");
        list.add("eee");
        return list;
    }

    @GetMapping("/getLongAndLati")
    public Object getLongAndLati(@RequestParam(value = "address") String address) throws Exception {
        Map<String, Double> map = new HashMap<String, Double>();
        //System.out.println("getlong");
        map = GetLngAndLatUtil.getLngAndLat(address);
        return map;
    }
    @GetMapping("/getSessionId")
    public Object getSessionId(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        System.out.println("session过期时间："+servletContext.getSessionTimeout());
        return session.getId();
    }

    public void main(){}


}
