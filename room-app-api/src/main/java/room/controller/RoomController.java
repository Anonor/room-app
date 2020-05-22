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
import room.pojo.vo.RoomGroupVO;
import room.pojo.vo.RoomVO;
import room.service.OrderService;
import room.service.RoomService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        JSONObject result = new JSONObject();
        Room room = new Room();
        room.setName(roomName);
        room.setFatherId(groupId);
        room.setPensionId(pensionId);
        if (roomService.isRoomExist(groupId,roomName)){
            result.put("status","failure");
            result.put("detail","该房间已存在，请重新添加！");
        }else {
            roomService.createRoom(room);
            int roomId = roomService.getRoomIdByPensionIdAndRoomName(pensionId,roomName);
            result.put("roomId",roomId);
            result.put("status","success");
            result.put("detail","房间添加成功！");
        }

        return result.toJSONString();

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
        List<RoomVO> roomVOS = new ArrayList<>();
        for (Room room:rooms){
            RoomVO roomVO = new RoomVO();
            roomVO.setRoomName(room.getName());
            roomVO.setRoomId(room.getRoomId());
            roomVO.setRoomStatus(room.getRoomStatus());
            roomVOS.add(roomVO);
        }
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail",roomVOS);
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

    //根据民宿ID显示所有分组
    @RequestMapping(value = "getGroupsInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getGroupsInfo(@RequestBody RoomBO roomBO){
        int pensionId = roomBO.getPensionId();
        if (roomService.queryRoomGroupsByPensionId(pensionId).isEmpty()){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","该民宿没有分组信息！");
            return result.toJSONString();
        }
        List<RoomGroupVO> roomGroupVOS=new ArrayList<>();
        for (RoomGroupVO roomGroupVO:roomService.queryRoomGroupsByPensionId(pensionId)){
            RoomGroupVO roomGroupVO1 = new RoomGroupVO();
            roomGroupVO1.setGroupId(roomGroupVO.getGroupId());
            roomGroupVO1.setGroupName(roomGroupVO.getGroupName());
            roomGroupVOS.add(roomGroupVO1);
        }
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail",roomGroupVOS);
        return result.toJSONString();
    }

    //根据民宿ID显示所有房间
    @RequestMapping(value = "getRoomsInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getRoomsInfo(@RequestBody RoomBO roomBO){
        int pensionId = roomBO.getPensionId();
        List<Room> rooms = roomService.queryRoomsByPensionIdAndRoomStatus(pensionId,0);
        rooms.addAll(roomService.queryRoomsByPensionIdAndRoomStatus(pensionId,1));
        if (rooms.isEmpty()){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","该民宿没有房间信息！");
            return result.toJSONString();
        }
        List<RoomVO> roomVOS=new ArrayList<>();
        for (Room room:rooms){
            RoomVO roomVO = new RoomVO();
            roomVO.setRoomId(room.getRoomId());
            roomVO.setRoomName(room.getName());
            roomVOS.add(roomVO);
        }
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail",roomVOS);
        return result.toJSONString();
    }

    //根据房间ID获取组ID以及名字
    @RequestMapping(value = "getGroupInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getGroupInfo(@RequestBody RoomBO roomBO){
        int roomId = roomBO.getRoomId();
        RoomVO roomVO = roomService.queryRoomInfoByRoomId(roomId);
        RoomVO roomVO1 = new RoomVO();
        roomVO1.setGroupId(roomVO.getGroupId());
        roomVO1.setGroupName(roomVO.getGroupName());
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail",roomVO1);
        return result.toJSONString();
    }

    //根据分组id和房间状态查询房间列表
    @RequestMapping(value = "getRoomsInfoByGroupIdAndRoomStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getRoomsInfoByGroupIdAndRoomStatus(@RequestBody RoomBO roomBO){
        int groupId = roomBO.getGroupId();
        int status = roomBO.getStatus();
        boolean flag = false;
        List<Room> rooms = new ArrayList<>();
        List<RoomVO> roomVOS=new ArrayList<>();
        JSONObject result = new JSONObject();

        switch (status){
            //查询所有房间（不包括2）
            case 0:
                rooms = roomService.queryRoomsByGroupIdAndRoomStatus(groupId, 0);
                rooms.addAll(roomService.queryRoomsByGroupIdAndRoomStatus(groupId, 1));
                if (rooms.isEmpty()) {
                    result.put("status", "failure");
                    result.put("detail","该分组下没有房间信息！");
                    break;
                }
                flag = true;
                break;
            //查询空闲房间
            case 1:
                rooms = roomService.queryRoomsByGroupIdAndRoomStatus(groupId, 0);
                if (rooms.isEmpty()) {
                    result.put("status", "failure");
                    result.put("detail","该分组下没有空闲房间信息！");
                    break;
                }
                flag = true;
                break;
            //查询已经被入住房间
            case 2:
                rooms = roomService.queryRoomsByGroupIdAndRoomStatus(groupId, 1);
                if (rooms.isEmpty()) {
                    result.put("status", "failure");
                    result.put("detail","该分组下没有已入住房间信息！");
                    break;
                }
                flag = true;
                break;
            default:
                break;
        }

        if (flag){
            for (Room room:rooms){
                RoomVO roomVO = new RoomVO();
                roomVO.setRoomName(room.getName());
                roomVO.setRoomId(room.getRoomId());
                roomVO.setRoomStatus(room.getRoomStatus());
                roomVOS.add(roomVO);
            }
            result.put("status", "success");
            result.put("detail",roomVOS);
        }
        return result.toJSONString();
    }

    //添加分组
    @RequestMapping(value = "addGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  addGroup(@RequestBody RoomBO roomBO){
        int pensionId = roomBO.getPensionId();
        String groupName = roomBO.getGroupName();
        Room room = new Room();
        room.setPensionId(pensionId);
        room.setName(groupName);
        if (roomService.isGroupExist(pensionId,groupName)){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","该分组已存在，请重新添加！");
            return result.toJSONString();
        }
        roomService.createRoomGroup(room);
        int groupId = roomService.getGroupIdByPensionIdAndGroupName(pensionId,groupName);
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail","添加分组成功！");
        result.put("groupId",groupId);
        return result.toJSONString();
    }

    //修改组名
    @RequestMapping(value = "updateGroupName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  updateGroupName(@RequestBody RoomBO roomBO){
        int groupId = roomBO.getGroupId();
        int pensionId=roomBO.getPensionId();
        String groupName = roomBO.getGroupName();
        if (roomService.isGroupExist(pensionId,groupName)){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","该分组已存在或修改前后分组名相同，请重新修改分组名称或放弃修改组名！");
            return result.toJSONString();
        }
        Room room=new Room();
        room.setName(groupName);
        room.setRoomId(groupId);
        roomService.updateRoomOrGroup(room);
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail","修改组名成功！");
        return result.toJSONString();
    }

    //删除组（如果组下面有房间提醒先删除房间）
    @RequestMapping(value = "deleteGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  deleteGroup(@RequestBody RoomBO roomBO){
        int groupId = roomBO.getGroupId();
        JSONObject result = new JSONObject();
        //确保该分组下没有房间了
        if (roomService.queryRoomsByGroupIdAndRoomStatus(groupId,0).isEmpty() &&
                roomService.queryRoomsByGroupIdAndRoomStatus(groupId,1).isEmpty()){
            roomService.updateRoomStatusByRoomId(groupId,2);
            result.put("status","success");
            result.put("detail","删除分组成功！");
        }else {
            result.put("status","failure");
            result.put("detail","该分组下还有房间，请先删除房间！删除分组失败！");
        }
        return result.toJSONString();
    }

    //修改房间信息
    @RequestMapping(value = "updateRoomInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  updateRoomInfo(@RequestBody RoomBO roomBO){
        int roomId = roomBO.getRoomId();
        int groupId = roomBO.getGroupId();
        int type = roomBO.getType();
        String roomName = roomBO.getRoomName();
        JSONObject result = new JSONObject();

        if (roomService.isRoomExist(groupId,roomName)){
            result.put("status","failure");
            result.put("detail","若您移动了分组：在移动后的分组中已存在同名房间；" +
                    "若您修改了房间名：修改前后房间名称一样或该分组中存在的同名房间。故修改房间信息失败！请重新修改或放弃修改！");
        }else {
            //只修改房间名称
            if (type==0){
                Room room = new Room();
                room.setRoomId(roomId);
                room.setName(roomName);
                roomService.updateRoomOrGroup(room);
                result.put("status","success");
                result.put("detail","房间名称修改成功！");
            }else { //移动了分组
                Room room = new Room();
                room.setRoomId(roomId);
                room.setName(roomName);
                room.setFatherId(groupId);
                roomService.updateRoomOrGroup(room);
                result.put("status","success");
                result.put("detail","房间信息修改成功！");
            }
        }

        return result.toJSONString();
    }

    //删除房间（如果房间有住客提醒无法删除）
    @RequestMapping(value = "deleteRoom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  deleteRoom(@RequestBody RoomBO roomBO){
        int roomId = roomBO.getRoomId();
        JSONObject result = new JSONObject();
        //确保房间没有住客
        if (orderService.queryByRoomIdAndOrderStatus(roomId,1).isEmpty()){
            roomService.updateRoomStatusByRoomId(roomId,2);
            result.put("status","success");
            result.put("detail","删除房间成功！");
        }else {
            result.put("status","failure");
            result.put("detail","该房间有住客，无法删除房间！请先完成该订单！");
        }
        return result.toJSONString();
    }

}
