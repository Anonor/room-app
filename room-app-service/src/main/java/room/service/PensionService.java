package room.service;

import room.pojo.Pension;

import java.util.List;

public interface PensionService {

    //该商家该民宿名称是否存在（true：存在， false：不存在）
    public boolean isPensionExist(Integer merchantId, String pensionName);

    //创建民宿（默认设置状态为1）
    public void createPension(Pension pension);

    //获取所有民宿信息
    public List<Pension> queryAll();

    //根据商家Id获取所有民宿信息
    public List<Pension> queryByMerchantId(Integer merchantId);

    //根据民宿Id获取民宿信息
    public Pension queryByPensionId(Integer pensionId);

    //根据商家Id删除所有民宿
    public void deletePensionByMerchantId(Integer merchantId);

    //根据民宿Id删除民宿
    public void deletePensionByPensionId(Integer pensionId);

    //修改民宿信息（民宿id必须存在，其它的选择性存在，要改的存在，注意商家id不能修改）
    public void updatePension(Pension pension);

    //根据民宿Id修改民宿状态（正常->禁用     禁用->正常）
    public void updatePensionStatus(Integer pensionId);


}
