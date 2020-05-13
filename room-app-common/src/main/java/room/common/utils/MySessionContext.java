package room.common.utils;

import javax.servlet.http.HttpSession;
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
            sessionIdMap.remove(session.getId());
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
