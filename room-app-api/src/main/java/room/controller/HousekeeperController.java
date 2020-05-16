package room.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.pojo.Housekeeper;
import room.pojo.bo.HousekeeperBO;
import room.pojo.vo.HousekeeperVO;
import room.service.HouseKeeperService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/housekeeper")
public class HousekeeperController {

    @Autowired
    private HouseKeeperService houseKeeperService;

    //商家添加保洁人员
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  add(@RequestBody HousekeeperBO housekeeperBO){
        String name = housekeeperBO.getName();
        //查询该保洁人员是否已存在

        Housekeeper housekeeper = new Housekeeper();
        return "ok";

    }

    //根据民宿Id获取保洁人员列表
    @RequestMapping(value = "getHousekeepersByPensionId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getHousekeepersByPensionId(@RequestBody HousekeeperBO housekeeperBO){
        int pensionId = housekeeperBO.getPensionId();
        List<HousekeeperVO> housekeeperVOS=new ArrayList<>();
        for (Housekeeper housekeeper:houseKeeperService.queryHouseKeeperByPensionId(pensionId)){
            HousekeeperVO housekeeperVO = new HousekeeperVO();
            housekeeperVO.setHousekeeperId(housekeeper.getHousekeeperId());
            housekeeperVO.setName(housekeeper.getName());
            housekeeperVOS.add(housekeeperVO);
        }
        if (housekeeperVOS.isEmpty()){
            JSONObject result = new JSONObject();
            result.put("status","failure");
            result.put("detail","还没有保洁人员信息");
            return  result.toJSONString();
        }
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("detail",housekeeperVOS);
        return  result.toJSONString();

    }
}
