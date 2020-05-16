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
    public void createHouseKeeper() {

    }

    @Override
    public void deleteHouseKeeperByHouseKeeperId(Integer houseKeeperId) {

    }
}
