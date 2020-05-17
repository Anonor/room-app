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

    @Override
    public boolean isRoomExist(Integer groupId, String roomName) {
        Example example = new Example(Room.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fatherId", groupId);
        criteria.andEqualTo("roomName", roomName);
        return roomMapper.selectOneByExample(example) == null ? false : true;
    }

    @Override
    public void createRoom(Room room) {
        room.setRoomStatus(0);
        roomMapper.insert(room);
    }

    @Override
    public void createRoomGroup(Room room) {
        room.setRoomStatus(0);
        room.setFatherId(0);
        roomMapper.insert(room);
    }

    @Override
    public void updateRoomStatusByMerchantId(Integer merchantId, Integer roomStatus) {
        List<Room> rooms = roomMapper.selectRoomsByMerchantId(merchantId);
        for (Room room : rooms) {
            room.setRoomStatus(roomStatus);
            updateRoomOrGroup(room);
        }
    }

    @Override
    public void updateRoomStatusByRoomId(Integer roomId, Integer roomStatus) {
        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomStatus(roomStatus);
        roomMapper.updateByPrimaryKeySelective(room);
    }

    @Override
    public void updateRoomOrGroup(Room room) {
        roomMapper.updateByPrimaryKeySelective(room);
    }
}
