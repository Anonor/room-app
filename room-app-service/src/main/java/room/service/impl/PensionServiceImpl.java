package room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.PensionMapper;
import room.pojo.Pension;
import room.service.PensionService;
import tk.mybatis.mapper.entity.Example;

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
        Pension pension = pensionMapper.selectOneByExample(pansionExample);
        return pension == null ? false : true;
    }

    @Override
    public void createPension(Pension pension) {
        pension.setStatus(0);
        pensionMapper.insert(pension);
    }

    @Override
    public List<Pension> queryAll() {
        return pensionMapper.selectAll();
    }

    @Override
    public List<Pension> queryByMerchantId(Integer merchantId) {
        Example pansionExample = new Example(Pension.class);
        Example.Criteria criteria = pansionExample.createCriteria();
        criteria.andEqualTo("merchantId", merchantId);
        return pensionMapper.selectByExample(pansionExample);
    }

    @Override
    public Pension queryByPensionId(Integer pensionId) {
        return pensionMapper.selectByPrimaryKey(pensionId);
    }

    @Override
    public void deletePensionByMerchantId(Integer merchantId) {
        Example pansionExample = new Example(Pension.class);
        Example.Criteria criteria = pansionExample.createCriteria();
        criteria.andEqualTo("merchantId", merchantId);
        pensionMapper.deleteByExample(pansionExample);
    }

    @Override
    public void deletePensionByPensionId(Integer pensionId) {
        pensionMapper.deleteByPrimaryKey(pensionId);
    }

    @Override
    public void updatePension(Pension pension) {
        pensionMapper.updateByPrimaryKeySelective(pension);
    }

    @Override
    public void updatePensionStatus(Integer pensionId) {
        Pension pension = queryByPensionId(pensionId);
        int status = pension.getStatus();
        if (status == 0) {
            pension.setStatus(1);
            updatePension(pension);
        } else if (status == 1) {
            pension.setStatus(0);
            updatePension(pension);
        }
    }
}
