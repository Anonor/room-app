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
        int pensionId = housekeeperBO.getPensionId();
        JSONObject result = new JSONObject();
        //查询该保洁人员是否已存在
        if (houseKeeperService.isHouseKeeperExist(pensionId,name)){
            result.put("status","failure");
            result.put("detail","该保洁人员已存在，请重新添加！");
        }else {
            Housekeeper housekeeper = new Housekeeper();
            housekeeper.setName(name);
            housekeeper.setPensionId(pensionId);
            housekeeper.setPhone(housekeeperBO.getPhone());
            housekeeper.setWage(housekeeperBO.getWage());
            houseKeeperService.createHouseKeeper(housekeeper);
            result.put("housekeeperId",houseKeeperService.queryHouseKeeperIdByName(pensionId,name));
            result.put("status","success");
            result.put("detail","保洁人员添加成功！");
        }

        return result.toJSONString();

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
            housekeeperVO.setPhone(housekeeper.getPhone());
            housekeeperVO.setWage(housekeeper.getWage());
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

    //修改保洁人员信息
    @RequestMapping(value = "updateHousekeeperInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  updateHousekeeperInfo(@RequestBody HousekeeperBO housekeeperBO){
        JSONObject result = new JSONObject();
        int pensionId = housekeeperBO.getPensionId();
        String name = housekeeperBO.getName();
        if (houseKeeperService.isHouseKeeperExist(pensionId,name)){     //修改后的名字在原来已存在
            result.put("status","failure");
            result.put("detail","修改后的名字与已有保洁人员名字重复！修改失败！");
        }else {
            int housekeeperId = housekeeperBO.getHousekeeperId();
            Housekeeper housekeeper = new Housekeeper();
            housekeeper.setHousekeeperId(housekeeperId);
            housekeeper.setWage(housekeeperBO.getWage());
            housekeeper.setPhone(housekeeperBO.getPhone());
            housekeeper.setName(housekeeperBO.getName());
            houseKeeperService.updateHouseKeeper(housekeeper);
            result.put("status","success");
            result.put("detail","修改保洁人员信息成功！");
        }
        return result.toJSONString();
    }

    //删除保洁人员
    @RequestMapping(value = "deleteHousekeeper", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  deleteHousekeeper(@RequestBody HousekeeperBO housekeeperBO){
        JSONObject result = new JSONObject();
        int housekeeperId = housekeeperBO.getHousekeeperId();
        houseKeeperService.deleteHouseKeeperByHouseKeeperId(housekeeperId);
        result.put("status","success");
        result.put("detail","已删除该保洁人员！");
        return result.toJSONString();
    }

}
