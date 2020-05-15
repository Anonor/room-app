package room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.pojo.Housekeeper;
import room.pojo.bo.HousekeeperBO;
import room.service.HouseKeeperService;

@RestController
@RequestMapping("/housekeeper")
public class HousekeeperController {

    @Autowired
    private HouseKeeperService houseKeeperService;

    //商家添加保洁人员
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  register(@RequestBody HousekeeperBO housekeeperBO){
        String name = housekeeperBO.getName();
        //查询该保洁人员是否已存在

        Housekeeper housekeeper = new Housekeeper();
        return "ok";

    }
}
