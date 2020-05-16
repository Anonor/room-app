package room.service;

import room.pojo.Room;
import room.pojo.vo.RoomGroupVO;

import java.util.List;

public interface RoomService {

    //根据民宿Id获取其下房屋分组信息
    public List<RoomGroupVO> queryRoomGroupsByPensionId(Integer pensionId);

    //根据分组Id获取其下房间信息
    public List<Room> queryRoomsByGroupId(Integer groupId);
}
