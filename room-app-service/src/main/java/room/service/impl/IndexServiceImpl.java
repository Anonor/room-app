package room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.common.utils.DateUtils;
import room.mapper.OrdersMapper;
import room.mapper.RoomMapper;
import room.pojo.Orders;
import room.pojo.vo.PensionIncomeVO;
import room.pojo.vo.RoomIncomeVO;
import room.service.IndexService;

import java.text.ParseException;
import java.util.*;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public PensionIncomeVO queryPensionIncome(Integer pensionId, Date start, Date end) {
        Map<String, Object> map = new HashMap<>();
        map.put("pensionId", pensionId);
        map.put("startTime", start);
        map.put("endTime", end);
        return ordersMapper.selectPensionIncome(map);
    }

    @Override
    public List<RoomIncomeVO> queryRoomIncomesByPensionId(Integer pensionId, Date start, Date end) {
        Map<String, Object> map = new HashMap<>();
        map.put("pensionId", pensionId);
        map.put("startTime", start);
        map.put("endTime", end);
        List<RoomIncomeVO> result = ordersMapper.selectRoomIncome(map);
        for (RoomIncomeVO vo : result) {
            vo.setOccupancyRate(queryOccupancyRateByRoomIc(vo.getRoomId(), start, end));
        }
        //添加天数为0的房间信息
//        Set<Integer> set = new HashSet<>();
//        for (RoomIncomeVO vo : result) {
//            set.add(vo.getRoomId());
//        }
//        Example example = new Example(Room.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("pensionId", pensionId);
//        criteria.andNotEqualTo("fatherId", 0);
//        criteria.andNotEqualTo("roomStatus", 2);
//        List<Room> rooms = roomMapper.selectByExample(example);
//        for (Room room : rooms) {
//            if (!set.contains(room.getRoomId())) {
//                RoomIncomeVO vo = new RoomIncomeVO();
//                vo.setRoomId(room.getRoomId());
//                vo.setRoomName(room.getName());
//                vo.setDays(0);
//                vo.setIncome(0);
//                set.add(room.getRoomId());
//                result.add(vo);
//            }
//        }
        return result;
    }

    @Override
    public float queryOccupancyRateByRoomIc(Integer roomId, Date start, Date end) {
        float rate = 0;
        try {
            int allDays = DateUtils.getDays(start, end);
            float days = 0;
            Map<String, Object> map = new HashMap<>();
            map.put("roomId", roomId);
            map.put("startTime", start);
            map.put("endTime", end);
            List<Orders> ordersList = ordersMapper.selectByTime(map);
            if (ordersList.size() != 0) {
                for (Orders o : ordersList) {
                    Date s = o.getCheckInDate();
                    Date e = o.getCheckOutDate();
                    if (DateUtils.getDays(start, s) >= 0) {
                        if (DateUtils.getDays(end, e) > 0) {
                            days += DateUtils.getDays(s, end);
                        } else {
                            days += DateUtils.getDays(s, e);
                        }
                    } else {
                        days += DateUtils.getDays(start, e);
                    }
                }
                rate = days / allDays;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rate;
    }
}
