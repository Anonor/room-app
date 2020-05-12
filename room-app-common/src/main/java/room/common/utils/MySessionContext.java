package room.common.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class MySessionContext {

    private static HashMap<String,Object> sessionIdMap = new HashMap<String,Object>();
    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionIdMap.put(session.getId(), session);
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

    public static synchronized String getSessionId(){
        String sessionId = null;
        for (Map.Entry<String, Object> entry : sessionIdMap.entrySet()) {
            sessionId = entry.getKey();
            if (sessionId != null) {
                break;
            }
        }
        return  sessionId;
    }
}
