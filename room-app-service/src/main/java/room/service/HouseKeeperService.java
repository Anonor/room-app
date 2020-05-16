package room.service;

import room.pojo.Housekeeper;

import java.util.List;

public interface HouseKeeperService {

    //根据民宿id获取保洁人员列表
    public List<Housekeeper> queryHouseKeeperByPensionId(Integer pensionId);

    //根据保洁人员Id获取保洁人员名称
    public String queryHouseKeeperNameById(Integer houseKeeperId);

    //创建保洁人员信息
    public void createHouseKeeper();

    //删除保洁人员信息
    public void deleteHouseKeeperByHouseKeeperId(Integer houseKeeperId);

}
