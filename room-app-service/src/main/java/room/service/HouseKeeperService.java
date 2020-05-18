package room.service;

import room.pojo.Housekeeper;

import java.util.List;

public interface HouseKeeperService {

    //根据民宿id获取保洁人员列表
    public List<Housekeeper> queryHouseKeeperByPensionId(Integer pensionId);

    //根据保洁人员Id获取保洁人员名称
    public String queryHouseKeeperNameById(Integer houseKeeperId);

    //根据民宿id和保洁人员名字name判断保洁人员是否存在（true：存在   false：不存在）
    public boolean isHouseKeeperExist(Integer pensionId, String houseKeeperName);

    //创建保洁人员信息
    public void createHouseKeeper(Housekeeper housekeeper);

    //修改保洁人员信息
    public void updateHouseKeeper(Housekeeper housekeeper);

    //删除保洁人员信息
    public void deleteHouseKeeperByHouseKeeperId(Integer houseKeeperId);

}
