package room.mapper;

import org.apache.ibatis.annotations.Param;
import room.mymapper.MyMapper;
import room.pojo.Orders;
import room.pojo.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface OrdersMapper extends MyMapper<Orders> {

    List<OrderVO> queryOrderByPensionIdAndOrderStatus(@Param("paramMap") Map<String, Object> paramMap);

    OrderVO queryOrderByOrderId(Integer orderId);

    List<OrderVO> queryOrderByRoomIdAndOrderStatus(@Param("paramMap") Map<String, Object> paramMap);
}