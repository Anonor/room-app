package room.mapper;

import org.apache.ibatis.annotations.Param;
import room.mymapper.MyMapper;
import room.pojo.Orders;
import room.pojo.vo.OrderVO;
import room.pojo.vo.PensionIncomeVO;
import room.pojo.vo.RoomIncomeVO;

import java.util.List;
import java.util.Map;

public interface OrdersMapper extends MyMapper<Orders> {

    List<OrderVO> selectOrderByPensionIdAndOrderStatus(@Param("paramMap") Map<String, Object> paramMap);

    OrderVO selectOrderByOrderId(Integer orderId);

    List<OrderVO> selectOrderByRoomIdAndOrderStatus(@Param("paramMap") Map<String, Object> paramMap);

    PensionIncomeVO selectPensionIncome(Integer pensionId);

    List<RoomIncomeVO> selectRoomIncome(Integer pensionId);

    List<Orders> selectByTime(@Param("paramMap") Map<String, Object> paramMap);
}