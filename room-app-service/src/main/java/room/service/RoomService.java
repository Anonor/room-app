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

    //根据民宿Id和房间状态获取其下房间信息
    public List<Room> queryRoomsByPensionIdAndRoomStatus(Integer pensionId, Integer roomStatus);

    //根据分组Id和房间状态获取其下房间信息
    public List<Room> queryRoomsByGroupIdAndRoomStatus(Integer groupId, Integer roomStatus);

    //根据房间Id获取分组和房间信息
    public RoomVO queryRoomInfoByRoomId(Integer roomId);

    //根据分组id和房间名称name查询房间是否存在（true：存在   false：不存在）
    public boolean isRoomExist(Integer groupId, String roomName);

    //根据民宿id和房间分组名称name查询房间是否存在（true：存在   false：不存在）
    public boolean isGroupExist(Integer pensionId, String groupName);

    //根据民宿Id和房间名称name获取房间Id
    public Integer getRoomIdByPensionIdAndRoomName(Integer pensionId, String roomName);

    //根据民宿Id和房间分组名称name获取房间分组Id
    public Integer getGroupIdByPensionIdAndGroupName(Integer pensionId, String groupName);

    //创建房间信息（默认设置房间状态为0）
    public void createRoom(Room room);

    //创建房间分组信息（默认fatherId设置状态为0）
    public void createRoomGroup(Room room);

    //根据商家Id修改所有房间状态
    public void updateRoomStatusByMerchantId(Integer merchantId, Integer roomStatus);

    //根据房间(房间分组)Id修改房间（房间分组）状态
    public void updateRoomStatusByRoomId(Integer roomId, Integer roomStatus);

    //选择性修改房间或分组信息（必须包含roomId）
    public void updateRoomOrGroup(Room room);
}
