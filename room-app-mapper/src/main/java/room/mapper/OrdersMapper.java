package room.mapper;

import org.apache.ibatis.annotations.Param;
import room.mymapper.MyMapper;
import room.pojo.Orders;
import room.pojo.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface OrdersMapper extends MyMapper<Orders> {

    List<OrderVO> selectOrderByPensionIdAndOrderStatus(@Param("paramMap") Map<String, Object> paramMap);

    OrderVO selectOrderByOrderId(Integer orderId);

    List<OrderVO> selectOrderByRoomIdAndOrderStatus(@Param("paramMap") Map<String, Object> paramMap);
}