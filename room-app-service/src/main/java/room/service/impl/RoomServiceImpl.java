package room.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import room.mapper.RoomMapper;
import room.pojo.Room;
import room.pojo.vo.RoomGroupVO;
import room.pojo.vo.RoomVO;
import room.service.RoomService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<RoomGroupVO> queryRoomGroupsByPensionId(Integer pensionId) {
        Example example = new Example(Room.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pensionId", pensionId);
        criteria.andEqualTo("fatherId", 0);
        List<Room> rooms = roomMapper.selectByExample(example);
        List<RoomGroupVO> groups = new ArrayList<>();
        for (Room room : rooms) {
            RoomGroupVO roomGroupVO = new RoomGroupVO();
            BeanUtils.copyProperties(room, roomGroupVO);
            groups.add(roomGroupVO);
        }
        return groups;
    }

    @Override
    public List<RoomGroupVO> queryValidRoomGroupsByPensionId(Integer pensionId) {
        List<RoomGroupVO> result = roomMapper.selectValidRoomGroupsByPensionId(pensionId);
        return result;
    }

    @Override
    public List<Room> queryRoomsByGroupIdAndRoomStatus(Integer groupId, Integer roomStatus) {
        Example example = new Example(Room.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fatherId", groupId);
        criteria.andEqualTo("roomStatus", roomStatus);
        return roomMapper.selectByExample(example);
    }

    @Override
    public RoomVO queryRoomInfoByRoomId(Integer roomId) {
        return roomMapper.selectRoomInfoByRoomId(roomId);
    }
}
