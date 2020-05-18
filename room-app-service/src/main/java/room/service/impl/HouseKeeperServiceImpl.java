package room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.HousekeeperMapper;
import room.pojo.Housekeeper;
import room.pojo.Pension;
import room.service.HouseKeeperService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HouseKeeperServiceImpl implements HouseKeeperService {

    @Autowired
    private HousekeeperMapper housekeeperMapper;

    @Override
    public List<Housekeeper> queryHouseKeeperByPensionId(Integer pensionId) {
        Example example = new Example(Pension.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pensionId", pensionId);
        return housekeeperMapper.selectByExample(example);
    }

    @Override
    public String queryHouseKeeperNameById(Integer housekeeperId) {
        return housekeeperMapper.selectByPrimaryKey(housekeeperId).getName();
    }

    @Override
    public boolean isHouseKeeperExist(Integer pensionId, String houseKeeperName) {
        Example example = new Example(Pension.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pensionId", pensionId);
        criteria.andEqualTo("name", houseKeeperName);
        return housekeeperMapper.selectOneByExample(example) == null ? false : true;
    }

    @Override
    public void createHouseKeeper(Housekeeper housekeeper) {
        housekeeperMapper.insert(housekeeper);
    }

    @Override
    public void updateHouseKeeper(Housekeeper housekeeper) {
        housekeeperMapper.updateByPrimaryKeySelective(housekeeper);
    }

    @Override
    public void deleteHouseKeeperByHouseKeeperId(Integer houseKeeperId) {
        housekeeperMapper.deleteByPrimaryKey(houseKeeperId);
    }
}
