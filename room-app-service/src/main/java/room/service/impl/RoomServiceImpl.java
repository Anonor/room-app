package room.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import room.mapper.RoomMapper;
import room.pojo.Room;
import room.pojo.vo.RoomGroupVO;
import room.service.RoomService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

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
    public List<Room> queryRoomsByGroupId(Integer groupId) {
        Example example = new Example(Room.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fatherId", groupId);
        return roomMapper.selectByExample(example);
    }
}
