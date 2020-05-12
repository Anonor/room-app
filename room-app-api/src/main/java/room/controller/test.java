package room.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import room.common.utils.MySessionContext;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/test")
 public class test {

    @GetMapping("/getSessionIdTest")
    public static void getSessionId(HttpSession session){
        //存sessionId
        final String sessionId = session.getId();
        System.out.println("corectSessionId:"+sessionId);
        //存session
        MySessionContext.addSession(session);
        String sessionId2 = MySessionContext.getSessionId();
        System.out.println("sessionId2==============:"+sessionId2);
    }
}
