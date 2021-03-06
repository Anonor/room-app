package room.service;

import room.pojo.Merchant;

import java.util.List;

public interface MerchantService {

    //根据account查询id
    public Integer queryIdByAccount(String account);

    //根据商家id查询商家信息
    public Merchant queryMerchantById(Integer merchantId);

    //根据account查询商家信息
    public Merchant queryMerchantByAccount(String account);

    //查询所有商家信息（用于后端管理）
    public List<Merchant> queryAll();

    //根据状态查找所有的商家信息
    public List<Merchant> queryByMerchantStatus(Integer merchantStatus);

    //查询商家是否可以正常登录（true：正常   false：不正常，不能登录）
    public boolean isMerchantValid(Integer merchantId);

    //判断account是否存在（true：存在）
    public boolean isAccountExist(String account);

    //增加（注册）商家信息
    public Merchant createMerchant(Merchant merchant);

    //登录
    public boolean queryMerchantForLogin(String account, String password);

    //根据商家id修改商家信息（选择性修改，需要有商家id）
    public void updateMerchantById(Merchant merchant);

    //根据商家id修改商家密码（true：修改成功，false：旧密码不一致）
    public boolean updateMerchantPassword(Integer id, String oldPassword, String newPasseord);

    //根据商家id更改商家状态（status：需要更改为的状态码  0：禁用   1：正常）
    public void updateMerchantStatus(Integer merchantId, Integer status);

}
