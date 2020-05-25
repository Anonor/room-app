package room.common.utils;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;

public class MySessionContext {

    private static HashMap<String,Object> sessionIdMap = new HashMap<>();
    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionIdMap.put(session.getId(),session);
        }
    }

    public static synchronized void delSession(HttpSession session) {
        if (session != null) {
            //从全局变量中删掉
            sessionIdMap.remove(session.getId());
            //清空项目自带的session
            Enumeration em = session.getAttributeNames();
            while(em.hasMoreElements()){
                session.removeAttribute(em.nextElement().toString());
            }
        }
    }

    public static synchronized void delSessionById(String sessionId){
        HttpSession session = getSession(sessionId);
        delSession(session);
    }

    public static synchronized HttpSession getSession(String session_id) {
        if (sessionIdMap.containsKey(session_id)){
            return (HttpSession) sessionIdMap.get(session_id);
        }else{
            return null;
        }
    }
}
