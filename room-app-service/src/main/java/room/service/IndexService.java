package room.service;


import room.pojo.vo.PensionIncomeVO;
import room.pojo.vo.RoomIncomeVO;

import java.util.Date;
import java.util.List;

public interface IndexService {
    /**
     * 营收相关
     */

    //根据民宿id获取总营收
    public PensionIncomeVO queryPensionIncome(Integer pensionId);

    //根据民宿id获取房间的营收列表
    public List<RoomIncomeVO> queryRoomIncomesByPensionId(Integer pensionId);

    //根据房间id、开始时间和结束时间获取房间的入住率
    public float queryOccupancyRateByRoomIc(Integer roomId, Date start, Date end);

}
