package room.service;

import room.pojo.Order;

import java.util.List;

public interface OrderService {

    //根据商家Id获取订单信息
    public List<Order> queryByMerchantId(Integer merchantId);

    //根据商家Id删除所有订单
    public void deleteByMerchantId(Integer merchantId);

    //根据订单Id删除订单
    public void deleteByOrderId(Integer orderId);

    //创建订单
    public void createOrder(Order order);

    //根据商家Id查询某日期段的订单信息


}
