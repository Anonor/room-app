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

    //根据民宿状态获取民宿
    public List<Pension> queryByStatus(Integer pensionStatus);

    //根据商家Id获取所有民宿信息
    public List<Pension> queryByMerchantId(Integer merchantId);

    //根据民宿Id获取民宿信息
    public Pension queryByPensionId(Integer pensionId);

    /**
     * 根据商家Id和民宿状态修改所有民宿
     *      商家被禁用时候使用，更新所有状态正常的民宿状态为0（禁用）
     *      商家自行注销使用，更新所有状态正常的民宿状态为2（关闭）
     */
    public void deletePensionByMerchantId(Integer merchantId, Integer pensionStatus);

    //修改民宿信息（民宿id必须存在，其它的选择性存在，要改的存在，注意商家id不能修改）
    public void updatePension(Pension pension);

    //根据民宿Id修改民宿状态（0：禁用   1：正常   2：关闭）
    public void updatePensionStatus(Integer pensionId, Integer pensionStatus);

    //根据商家Id和民宿名称获取民宿Id
    public Integer getPensionId(Integer merchantId, String pensionName);

}
