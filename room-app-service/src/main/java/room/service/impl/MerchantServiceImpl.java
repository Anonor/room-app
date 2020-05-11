package room.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import room.mapper.MerchantMapper;
import room.pojo.Merchant;
import room.service.MerchantService;
import tk.mybatis.mapper.entity.Example;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryIdByAccount(String account) {
        Example merchantExample = new Example(Merchant.class);
        Example.Criteria merchantCriteria = merchantExample.createCriteria();
        merchantCriteria.andEqualTo("account", account);
        Merchant merchant = merchantMapper.selectOneByExample(merchantExample);
        return merchant.getMerchantId();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Merchant queryMerchantById(Integer merchantId) {
        Merchant merchant = merchantMapper.selectByPrimaryKey(merchantId);
        return merchant;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Merchant queryMerchantByAccount(String account) {
        Example merchantExample = new Example(Merchant.class);
        Example.Criteria merchantCriteria = merchantExample.createCriteria();
        merchantCriteria.andEqualTo("account", account);
        Merchant merchant = merchantMapper.selectOneByExample(merchantExample);
        return merchant;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isAccountExist(String account) {
        Example merchantExample = new Example(Merchant.class);
        Example.Criteria merchantCriteria = merchantExample.createCriteria();
        merchantCriteria.andEqualTo("account", account);
        Merchant merchant = merchantMapper.selectOneByExample(merchantExample);
        return merchant == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Merchant createMerchant(Merchant merchant) {
        merchantMapper.insert(merchant);
        return queryMerchantByAccount(merchant.getAccount());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryMerchantForLogin(String account, String password) {
        Example merchantExample = new Example(Merchant.class);
        Example.Criteria merchantCriteria = merchantExample.createCriteria();
        merchantCriteria.andEqualTo("account", account);
        merchantCriteria.andEqualTo("password", password);
        Merchant merchant = merchantMapper.selectOneByExample(merchantExample);
        return merchant == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateMerchantById(Merchant merchant) {
        merchantMapper.updateByPrimaryKeySelective(merchant);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateMerchantPassword(Integer merchantId, String oldPassword, String newPasseord) {
        Merchant merchant = queryMerchantById(merchantId);
        if (!StringUtils.equals(merchant.getPassword(), oldPassword)) {
            return false;
        }
        merchant.setPassword(newPasseord);
        updateMerchantById(merchant);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteMerchant(Integer merchantId) {
        merchantMapper.deleteByPrimaryKey(merchantId);
    }

}
