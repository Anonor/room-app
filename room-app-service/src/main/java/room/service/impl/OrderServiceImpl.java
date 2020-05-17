package room.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.OrdersMapper;
import room.pojo.Orders;
import room.pojo.vo.OrderVO;
import room.service.HouseKeeperService;
import room.service.OrderService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper orderMapper;

    @Autowired
    private HouseKeeperService houseKeeperService;

    @Override
    public List<OrderVO> queryByMerchantId(Integer merchantId, Integer orderStatus) {
        return null;
    }

    @Override
    public List<OrderVO> queryByPensionId(Integer pensionId, Integer orderStatus) {
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pensionId", pensionId);
        criteria.andEqualTo("orderStatus", orderStatus);
        List<Orders> orders = orderMapper.selectByExample(example);
        List<OrderVO> result = new ArrayList<>();
        for (Orders order : orders) {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);
            orderVO.setHousekeeperName(houseKeeperService.queryHouseKeeperNameById(orderVO.getHousekeeperId()));
            result.add(orderVO);
        }
        return result;
    }

    @Override
    public OrderVO queryByOrderId(Integer orderId) {
        Orders order = orderMapper.selectByPrimaryKey(orderId);
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        orderVO.setHousekeeperName(houseKeeperService.queryHouseKeeperNameById(orderVO.getHousekeeperId()));
        return orderVO;
    }

    @Override
    public Orders queryOrdersByOrderId(Integer orderId) {
        Orders order = orderMapper.selectByPrimaryKey(orderId);
        return order;
    }

    @Override
    public void deleteByMerchantId(Integer merchantId) {
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("merchantId", merchantId);
        List<Orders> orders = orderMapper.selectByExample(example);
        for (Orders order : orders) {
            order.setOrderStatus(2);
            updateOrder(order);
        }
    }

    @Override
    public void updateOrderStatus(Integer orderId, Integer orderStatus) {
        Orders order = orderMapper.selectByPrimaryKey(orderId);
        order.setOrderStatus(orderStatus);
        updateOrder(order);
    }

    @Override
    public void createOrder(Orders order) {
        order.setOrderStatus(1);
        orderMapper.insert(order);
    }

    @Override
    public void updateOrder(Orders order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
