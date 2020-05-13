package room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.OrderMapper;
import room.pojo.Order;
import room.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> queryByMerchantId(Integer merchantId) {
        return null;
    }

    @Override
    public void deleteByMerchantId(Integer merchantId) {

    }

    @Override
    public void deleteByOrderId(Integer orderId) {

    }

    @Override
    public void createOrder(Order order) {

    }
}
