package room.service;


import room.pojo.vo.PensionIncomeVO;
import room.pojo.vo.RoomIncomeVO;

import java.util.Date;
import java.util.List;

public interface IndexService {
    /**
     * 营收相关
     */

    //根据民宿id、开始时间和结束时间获取总营收（只算已完成订单）
    public PensionIncomeVO queryPensionIncome(Integer pensionId, Date start, Date end);

    //根据民宿id、开始时间和结束时间获取房间的营收列表（包含房间的入住率）
    public List<RoomIncomeVO> queryRoomIncomesByPensionId(Integer pensionId,  Date start, Date end);

    //根据房间id、开始时间和结束时间获取房间的入住率
    public float queryOccupancyRateByRoomIc(Integer roomId, Date start, Date end);

}
