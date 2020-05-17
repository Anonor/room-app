package room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.PensionMapper;
import room.pojo.Pension;
import room.service.PensionService;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class PensionServiceImpl implements PensionService {

    @Autowired
    private PensionMapper pensionMapper;

    @Override
    public boolean isPensionExist(Integer merchantId, String pensionName) {
        Example pansionExample = new Example(Pension.class);
        Example.Criteria criteria = pansionExample.createCriteria();
        criteria.andEqualTo("merchantId", merchantId);
        criteria.andEqualTo("name", pensionName);
        criteria.andEqualTo("pensionStatus", 1);
        Pension pension = pensionMapper.selectOneByExample(pansionExample);
        return pension == null ? false : true;
    }

    @Override
    public void createPension(Pension pension) {
        pension.setPensionStatus(1);
        pension.setCreateTime(new Date());
        pension.setUpdateTime(new Date());
        pensionMapper.insert(pension);
    }

    @Override
    public List<Pension> queryAll() {
        return pensionMapper.selectAll();
    }

    @Override
    public List<Pension> queryByStatus(Integer pensionStatus) {
        Example pensionExample = new Example(Pension.class);
        Example.Criteria criteria = pensionExample.createCriteria();
        criteria.andEqualTo("pensionStatus", pensionStatus);
        List<Pension> result = pensionMapper.selectByExample(pensionExample);
        return result;
    }

    @Override
    public List<Pension> queryByMerchantId(Integer merchantId) {
        Example pensionExample = new Example(Pension.class);
        Example.Criteria criteria = pensionExample.createCriteria();
        criteria.andEqualTo("merchantId", merchantId);
        return pensionMapper.selectByExample(pensionExample);
    }

    @Override
    public Pension queryByPensionId(Integer pensionId) {
        return pensionMapper.selectByPrimaryKey(pensionId);
    }

    @Override
    public void deletePensionByMerchantId(Integer merchantId, Integer pensionStatus) {
        Example pensionExample = new Example(Pension.class);
        Example.Criteria criteria = pensionExample.createCriteria();
        criteria.andEqualTo("merchantId", merchantId);
        criteria.andEqualTo("pensionStatus", 1);
        List<Pension> pensions = pensionMapper.selectByExample(pensionExample);
        for (Pension pension : pensions) {
            pension.setPensionStatus(pensionStatus);
            updatePension(pension);
        }
    }

    @Override
    public void updatePension(Pension pension) {
        pension.setUpdateTime(new Date());
        pensionMapper.updateByPrimaryKeySelective(pension);
    }

    @Override
    public void updatePensionStatus(Integer pensionId, Integer pensionStatus) {
        Pension pension = queryByPensionId(pensionId);
        pension.setUpdateTime(new Date());
        pension.setPensionStatus(pensionStatus);
        updatePension(pension);
    }

    @Override
    public Integer getPensionId(Integer merchantId, String pensionName) {
        Example pensionExample = new Example(Pension.class);
        Example.Criteria criteria = pensionExample.createCriteria();
        criteria.andEqualTo("merchantId", merchantId);
        criteria.andEqualTo("pensionName", pensionName);
        return pensionMapper.selectOneByExample(pensionExample).getPensionId();
    }
}
