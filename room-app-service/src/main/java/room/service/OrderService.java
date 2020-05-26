package room.service;

import room.pojo.Orders;
import room.pojo.vo.OrderVO;

import java.util.List;

public interface OrderService {

    //根据商家Id和订单状态status获取订单信息   0：已完成   1：待退房（前端想要的）   2：订单无效
    public List<OrderVO> queryByMerchantId(Integer merchantId, Integer orderStatus);

    //根据民宿Id和订单状态status获取订单信息   0：已完成   1：待退房（前端想要的）   2：订单无效
    public List<OrderVO> queryByPensionIdAndOrderStatus(Integer pensionId, Integer orderStatus);

    //根据房间Id和订单状态status获取订单信息   0：已完成   1：待退房（前端想要的）   2：订单无效
    public List<OrderVO> queryByRoomIdAndOrderStatus(Integer roomId, Integer orderStatus);

    //根据订单Id获取订单详情信息（返回OrderVO）
    public OrderVO queryByOrderId(Integer orderId);

    //根据客源Id判断是否存在未完成订单（true：存在   false：不存在）
    public boolean isUnfinishedOrderExistBySourceId(Integer sourceId);

    //根据订单Id获取订单详情信息（返回orders)
    public Orders queryOrdersByOrderId(Integer orderId);

    //根据商家Id删除所有订单（这里是修改所有订单状态为2：订单无效）
    public void deleteByMerchantId(Integer merchantId);

    /**
     * 根据订单Id和订单状态修改订单
     * 功能：
     *      退房后完成订单：修改订单状态为0：已退房（订单完成）
     *      商家删除订单（这里是修改订单状态为2：订单无效）
     */
    public void updateOrderStatus(Integer orderId, Integer orderStatus);

    //创建订单（设置订单状态为1：待退房（入住中））
    public void createOrder(Orders order);

    //修改订单信息
    public void updateOrder(Orders order);

}
