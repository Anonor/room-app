package room.service;

import room.pojo.Room;
import room.pojo.vo.RoomGroupVO;

import java.util.List;

public interface RoomService {

    //根据民宿Id获取其下所有房屋分组信息（包含其下房间信息）
    public List<RoomGroupVO> queryRoomGroupsByPensionId(Integer pensionId);

    //根据民宿Id获取其下有空房的房屋分组信息（包含其下房间信息）
    public List<RoomGroupVO> queryValidRoomGroupsByPensionId(Integer pensionId);

    //根据分组Id和房间状态获取其下房间信息
    public List<Room> queryRoomsByGroupIdAndRoomStatus(Integer groupId, Integer roomStatsu);
}
