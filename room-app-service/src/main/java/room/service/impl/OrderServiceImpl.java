package room.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.OrderMapper;
import room.pojo.Order;
import room.pojo.vo.OrderVO;
import room.service.HouseKeeperService;
import room.service.OrderService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private HouseKeeperService houseKeeperService;

    @Override
    public List<OrderVO> queryByMerchantId(Integer merchantId, Integer orderStatus) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("merchantId", merchantId);
        criteria.andEqualTo("orderStatus", orderStatus);
        List<Order> orders = orderMapper.selectByExample(example);
        List<OrderVO> result = new ArrayList<>();
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);
            orderVO.setHousekeeperName(houseKeeperService.queryHouseKeeperNameById(orderVO.getHousekeeperId()));
            result.add(orderVO);
        }
        return result;
    }

    @Override
    public List<OrderVO> queryByPensionId(Integer pensionId, Integer orderStatus) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pensionId", pensionId);
        criteria.andEqualTo("orderStatus", orderStatus);
        List<Order> orders = orderMapper.selectByExample(example);
        List<OrderVO> result = new ArrayList<>();
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);
            orderVO.setHousekeeperName(houseKeeperService.queryHouseKeeperNameById(orderVO.getHousekeeperId()));
            result.add(orderVO);
        }
        return result;
    }

    @Override
    public OrderVO queryByOrderId(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        orderVO.setHousekeeperName(houseKeeperService.queryHouseKeeperNameById(orderVO.getHousekeeperId()));
        return orderVO;
    }

    @Override
    public void deleteByMerchantId(Integer merchantId) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("merchantId", merchantId);
        List<Order> orders = orderMapper.selectByExample(example);
        for (Order order : orders) {
            order.setOrderStatus(2);
            updateOrder(order);
        }
    }

    @Override
    public void updateOrderStatus(Integer orderId, Integer orderStatus) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setOrderStatus(orderStatus);
        updateOrder(order);
    }

    @Override
    public void createOrder(Order order) {
        order.setOrderStatus(1);
        orderMapper.insert(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
