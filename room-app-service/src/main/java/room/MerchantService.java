package room;

import room.pojo.Merchant;

public interface MerchantService {

    //插入商家信息
    void insert(Merchant merchant);
    //删除商家信息
    void delete(int id);
    //修改商家信息
    void update(int id);
    //查询商家信息
    Merchant select(int id);
}
