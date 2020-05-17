package room.mapper;

import room.pojo.Room;
import room.mymapper.MyMapper;
import room.pojo.vo.RoomGroupVO;
import room.pojo.vo.RoomVO;

import java.util.List;

public interface RoomMapper extends MyMapper<Room> {

    public List<RoomGroupVO> selectValidRoomGroupsByPensionId(Integer pensionId);

    public RoomVO selectRoomInfoByRoomId(Integer roomId);

    public List<Room> selectRoomsByMerchantId(Integer merchantId);

}