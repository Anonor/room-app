package room.service;

import room.pojo.Housekeeper;

import java.util.List;

public interface HouseKeeperService {

    //根据民宿id获取保洁列表
    public List<Housekeeper> queryHouseKeeperByPensionId(Integer pensionId);

}
