package room.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.pojo.Source;
import room.service.SourceService;

import java.util.List;

@RestController
@RequestMapping("/source")
public class SourceController {
    @Autowired
    private SourceService sourceService;

    //获取所有房源信息
    @RequestMapping(value = "getAllSourcesInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getAllSourcesInfo(){
        List<Source> sources = sourceService.queryAllSource();
        if (sources.isEmpty()){
            JSONObject result = new JSONObject();
            result.put("status","failure");
            result.put("detail","还没有房源信息");
            return  result.toJSONString();
        }
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("detail",sources);
        return  result.toJSONString();
    }
}
