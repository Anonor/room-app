package room.service;

import room.pojo.Room;
import room.pojo.vo.RoomGroupVO;
import room.pojo.vo.RoomVO;

import java.util.List;

public interface RoomService {

    //根据民宿Id获取其下所有房屋分组信息
    public List<RoomGroupVO> queryRoomGroupsByPensionId(Integer pensionId);

    //根据民宿Id获取其下有空房的房屋分组信息
    public List<RoomGroupVO> queryValidRoomGroupsByPensionId(Integer pensionId);

    //根据分组Id和房间状态获取其下房间信息
    public List<Room> queryRoomsByGroupIdAndRoomStatus(Integer groupId, Integer roomStatus);

    //根据房间Id获取分组和房间信息
    public RoomVO queryRoomInfoByRoomId(Integer roomId);

    //创建房间信息


    //根据商家Id修改所有房间状态
    public void updateRoomStatusByMerchantId(Integer merchantId);

    //根据房间Id修改房间状态
    public void updateRoomStatusByRoomId(Integer roomId);
}
