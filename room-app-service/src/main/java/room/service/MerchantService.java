package room.service;

import room.pojo.Merchant;

public interface MerchantService {

    //根据account查询id
    public Integer queryIdByAccount(Integer account);

    //根据商家id查询商家信息
    public Merchant queryMerchantById(Integer merchantId);

    //根据account查询商家信息
    public Merchant queryMerchantByAccount(Integer account);

    //判断account是否存在（true：存在）
    public boolean isAccountExist(Integer account);

    //增加（注册）商家信息
    public Merchant createMerchant(Merchant merchant);

    //登录
    public boolean queryMerchantForLogin(Integer account, String password);

    //根据商家id修改商家信息（选择性修改，需要有商家id）
    public void updateMerchantById(Merchant merchant);

    ////根据商家id修改商家密码（true：修改成功，false：旧密码不一致）
    public boolean updateMerchantPassword(Integer id, String oldPassword, String newPasseord);

    //根据商家id删除商家信息
    public void deleteMerchant(Integer merchantId);

}
