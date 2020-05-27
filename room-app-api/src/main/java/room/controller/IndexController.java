package room.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.pojo.bo.PensionBO;
import room.pojo.bo.RoomBO;
import room.pojo.vo.PensionIncomeVO;
import room.pojo.vo.RoomIncomeVO;
import room.service.IndexService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    IndexService indexService;

    //根据民宿ID以及开始结束时间获取总营收
    @RequestMapping(value = "getPensionIncome", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getPensionIncome(@RequestBody PensionBO pensionBO){
        int pensionId=pensionBO.getPensionId();
        Date startDate = pensionBO.getStartDate();
        Date endDate = pensionBO.getEndDate();
        JSONObject result = new JSONObject();
        PensionIncomeVO pensionIncomeVO = indexService.queryPensionIncome(pensionId, startDate, endDate);
        if (pensionIncomeVO == null){
            result.put("status","failure");
            result.put("detail","您的民宿暂时还没有营收信息！");
        }else {
            result.put("status","success");
            result.put("detail",pensionIncomeVO);
        }
        return result.toJSONString();
    }

    //根据民宿ID 以及开始结束时间获取(入住天数不为0的)房间的营收列表
    @RequestMapping(value = "getRoomIncomeList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getRoomIncomeList(@RequestBody PensionBO pensionBO){
        int pensionId=pensionBO.getPensionId();
        Date startDate = pensionBO.getStartDate();
        Date endDate = pensionBO.getEndDate();
        JSONObject result = new JSONObject();
        List<RoomIncomeVO> roomIncomeVOS = indexService.queryRoomIncomesByPensionId(pensionId, startDate, endDate);
        if (roomIncomeVOS.isEmpty()){
            result.put("status","failure");
            result.put("detail","您的民宿暂时还没有房间信息！");
        }else {
            result.put("status","success");
            result.put("detail",roomIncomeVOS);
        }
        return result.toJSONString();
    }

    //根据房间ID开始时间和结束时间获取房间的入住率
    @RequestMapping(value = "getOccupancyRate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getOccupancyRate(@RequestBody RoomBO roomBO){
        int roomId=roomBO.getRoomId();
        Date startDate = roomBO.getStartDate();
        Date endDate = roomBO.getEndDate();
        JSONObject result = new JSONObject();
        Float occupancyRate = indexService.queryOccupancyRateByRoomIc(roomId,startDate,endDate);
        result.put("status","success");
        result.put("occupancyRate",occupancyRate);
        return result.toJSONString();
    }

}
