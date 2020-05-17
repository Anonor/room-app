package room.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.pojo.Room;
import room.pojo.bo.RoomBO;
import room.pojo.vo.RoomGroupVO;
import room.pojo.vo.RoomVO;
import room.service.RoomService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    //商家添加房间
    @RequestMapping(value = "addRoom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  addRoom(@RequestBody RoomBO roomBO){
        String roomName = roomBO.getRoomName();
        int groupId = roomBO.getGroupId();
        int pensionId = roomBO.getPensionId();
        Room room = new Room();
        room.setName(roomName);
        room.setFatherId(groupId);
        room.setPensionId(pensionId);

        return "ok";

    }

    //获取所有房态信息
    @RequestMapping(value = "getAllRoomsInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getAllRoomsInfo(@RequestBody RoomBO roomBO){
        int pensionId = roomBO.getPensionId();
        List<RoomGroupVO> roomGroupVOS = roomService.queryRoomGroupsByPensionId(pensionId);
        List<RoomVO> roomVOS = new ArrayList<>();
        for (RoomGroupVO roomGroupVO:roomGroupVOS){
            RoomVO roomVO = new RoomVO();
            //roomVO.setRoomStatus(roomGroupVO);
        }
        return "ok";
    }

    //获取所有房态信息
    @RequestMapping(value = "getRoomAndGroupInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getRoomAndGroupInfo(@RequestBody RoomBO roomBO){
        int roomId = roomBO.getRoomId();
        //roomService.
        return "ok";
    }
}
