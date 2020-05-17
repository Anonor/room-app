package room.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.pojo.Room;
import room.pojo.bo.RoomBO;
import room.pojo.vo.OrderVO;
import room.pojo.vo.RoomVO;
import room.service.OrderService;
import room.service.RoomService;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    OrderService orderService;

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
    @RequestMapping(value = "getAllRoomsStatusInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getAllRoomsStatusInfo(@RequestBody RoomBO roomBO){
        int pensionId = roomBO.getPensionId();
        List<Room> rooms = roomService.queryRoomsByPensionIdAndRoomStatus(pensionId,0);
        rooms.addAll(roomService.queryRoomsByPensionIdAndRoomStatus(pensionId,1));
        if (rooms.isEmpty()){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","该民宿还没有房间，故不存在房态信息！");
            return result.toJSONString();
        }
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail",rooms);
        return result.toJSONString();
    }

    //根据房间id获取组以及房间信息
    @RequestMapping(value = "getRoomAndGroupInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getRoomAndGroupInfo(@RequestBody RoomBO roomBO){
        int roomId = roomBO.getRoomId();
        RoomVO roomVO = roomService.queryRoomInfoByRoomId(roomId);
        if (roomVO==null){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","该房间不存在，请重新检查！");
            return result.toJSONString();
        }
        RoomVO roomVO1 = new RoomVO();
        roomVO1.setGroupId(roomVO.getGroupId());
        roomVO1.setGroupName(roomVO.getGroupName());
        roomVO1.setRoomName(roomVO.getRoomName());
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail",roomVO1);
        return result.toJSONString();
    }

    //根据房间id获取未退房的订单详情
    @RequestMapping(value = "getOrderInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getOrderInfo(@RequestBody RoomBO roomBO){
        int roomId = roomBO.getRoomId();
        //订单状态1：待退房
        if (orderService.queryByRoomIdAndOrderStatus(roomId,1).isEmpty()){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","该房间没有未退房的订单！");
            return result.toJSONString();
        }
        OrderVO orderVO = orderService.queryByRoomIdAndOrderStatus(roomId,1).get(0);
        if (orderVO.getRemarks()==null){
            orderVO.setRemarks("无备注信息");
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        orderVO.setCheckInDate_(sdf.format(orderVO.getCheckInDate()));
        orderVO.setCheckOutDate_(sdf.format(orderVO.getCheckOutDate()));
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail",orderVO);
        return result.toJSONString();

    }

}
