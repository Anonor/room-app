package room.common.utils;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过百度地图API获取地址经纬度
 */
public class GetLngAndLatUtil {

    public static Map<String, Double> getLngAndLat(String address) throws Exception {
        Map<String, Double> map = new HashMap<String, Double>();
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address
                + "&output=json&ak=5bSX6lqF6irleeYplWXQVTFYz4tgw2Is";
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

        String str = json.toString();
        if (StringUtils.isNotEmpty(str)) {
            int lngStart = str.indexOf("lng\":");
            int lngEnd = str.indexOf(",\"lat");
            int latEnd = str.indexOf("},\"precise");
            if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                Double lng = Double.parseDouble(str.substring(lngStart + 5, lngEnd));
                Double lat = Double.parseDouble(str.substring(lngEnd + 7, latEnd));
                map.put("lng", lng);
                map.put("lat", lat);
                return map;
            }
        }

        return map;
    }

    /*public static void main(String[] args) throws Exception {
        getLngAndLat("人民中路三段1号(壹号公馆)");
    }*/

}