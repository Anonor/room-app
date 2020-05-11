package room.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.common.utils.GetLngAndLatUtil;
import room.pojo.Pension;
import room.pojo.bo.PensionBO;
import room.service.PensionService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pension")
public class PensionController {

    @Autowired
    private PensionService pensionService;

    //商家增加民宿
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String add(@RequestBody PensionBO pensionBO,
                      HttpServletRequest request){
        int merchantId = Integer.parseInt(request.getSession().getAttribute("id").toString());
        System.out.println("merchantID:"+merchantId);
        System.out.println("pensionName:"+pensionBO.getName());
        if(pensionService.isPensionExist(merchantId,pensionBO.getName())){
            System.out.println("民宿存在");
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","民宿已存在，增加民宿失败！");
            return result.toJSONString();
        }
        System.out.println("民宿不存在");
        Pension pension = new Pension();
        pension.setMerchantId(merchantId);
        pension.setName(pensionBO.getName());
        pension.setPrice(pensionBO.getPrice());
        pension.setAddressProvince(pensionBO.getAddressProvince());
        pension.setAddressCity(pensionBO.getAddressCity());
        pension.setAddressDistrict(pensionBO.getAddressDistrict());
        pension.setAddressDetail(pensionBO.getAddressDetail());
        //拼接地址得到该地址的经纬度
        String address = pensionBO.getAddressProvince()+pensionBO.getAddressCity()
                +pensionBO.getAddressDistrict()+ pensionBO.getAddressDetail();
        Map<String, Double> map = new HashMap<String, Double>();
        try {
            map = GetLngAndLatUtil.getLngAndLat(address);
            System.out.println("map:"+map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pension.setLongitude(map.get("lng").toString());
        pension.setLatitude(map.get("lat").toString());
        pensionService.createPension(pension);
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail","添加民宿成功！");
        return result.toJSONString();
    }
}
