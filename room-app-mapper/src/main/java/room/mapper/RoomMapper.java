package room.mapper;

import room.pojo.Room;
import room.mymapper.MyMapper;
import room.pojo.vo.RoomGroupVO;

import java.util.List;

public interface RoomMapper extends MyMapper<Room> {

    public List<RoomGroupVO> queryValidRoomGroupsByPensionId(Integer pensionId);

}