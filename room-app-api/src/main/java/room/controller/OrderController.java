package room.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.common.utils.MySessionContext;
import room.pojo.Orders;
import room.pojo.bo.OrderBO;
import room.pojo.vo.OrderVO;
import room.service.OrderService;
import room.service.RoomService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomService roomService;

    //商家添加订单
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  add(@RequestBody OrderBO orderBO){
        HttpSession session = MySessionContext.getSession(orderBO.getSessionId());
        int merchantId = Integer.parseInt(session.getAttribute("id").toString());
        Orders orders=new Orders();
        orders.setCheckInDate(orderBO.getCheckInDate());
        orders.setCheckOutDate(orderBO.getCheckOutDate());
        orders.setGuestName(orderBO.getGuestName());
        orders.setGuestPhone(orderBO.getGuestPhone());
        orders.setHousekeeperId(orderBO.getHousekeeperId());
        orders.setIncome(orderBO.getIncome());
        orders.setMerchantId(merchantId);
        orders.setPensionId(orderBO.getPensionId());
        orders.setRemarks(orderBO.getRemarks());
        orders.setRoomId(orderBO.getRoomId());
        orders.setSourceId(orderBO.getSourceId());
        orderService.createOrder(orders);
        //设置被入住房间状态为1：被入住
        roomService.updateRoomStatusByRoomId(orderBO.getRoomId(),1);
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("detail","入住成功！");
        return  result.toJSONString();
    }

    //商家获取已存在的订单（orderStatus=1）
    @RequestMapping(value = "getExistedOrdersByPensionId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getExistedOrdersByPensionId(@RequestBody OrderBO orderBO){
        int pensionId = orderBO.getPensionId();
        List<OrderVO> orderVOS=new ArrayList<>();
        for (OrderVO orderVO:orderService.queryByPensionIdAndOrderStatus(pensionId,1)){
            OrderVO orderVO1=new OrderVO();
            orderVO1.setOrderId(orderVO.getOrderId());
            orderVO1.setGuestName(orderVO.getGuestName());
            orderVO1.setGroupName(orderVO.getGroupName());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            orderVO1.setCheckInDate_(sdf.format(orderVO.getCheckInDate()));
            orderVO1.setCheckOutDate_(sdf.format(orderVO.getCheckOutDate()));
            orderVO1.setRoomName(orderVO.getRoomName());
            orderVOS.add(orderVO1);
        }
        if (orderVOS.isEmpty()){
            JSONObject result = new JSONObject();
            result.put("status","failure");
            result.put("detail","您的民宿还没有订单信息！");
            return  result.toJSONString();
        }
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("detail",orderVOS);
        return  result.toJSONString();

    }

    //根据orderId获取订单详情
    @RequestMapping(value = "getOrderDetailByOrderId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  getOrderDetailByOrderId(@RequestBody OrderBO orderBO){
        int orderId = orderBO.getOrderId();
        OrderVO orderVO=orderService.queryByOrderId(orderId);
        if (orderVO==null){
            JSONObject result = new JSONObject();
            result.put("status","failure");
            result.put("detail","不存在该订单");
            return  result.toJSONString();
        }
        if (orderVO.getRemarks()==null){
            orderVO.setRemarks("无备注信息");
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        orderVO.setCheckInDate_(sdf.format(orderVO.getCheckInDate()));
        orderVO.setCheckOutDate_(sdf.format(orderVO.getCheckOutDate()));
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("detail",orderVO);
        return  result.toJSONString();
    }

    //修改订单信息
    @RequestMapping(value = "updateOrderInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  updateOrderInfo(@RequestBody OrderBO orderBO){
        Orders orders=new Orders();
        orders.setOrderId(orderBO.getOrderId());
        //修改房间之后，将原入住房间状态置0，现入住房间状态置1
        orders.setRoomId(orderBO.getRoomId());
        roomService.updateRoomStatusByRoomId(orderBO.getRoomId(),1);    //现入住房间
        roomService.updateRoomStatusByRoomId(orderService.queryByOrderId(orderBO.getOrderId()).getRoomId(),0);     //原入住房间
        orders.setGuestName(orderBO.getGuestName());
        orders.setCheckInDate(orderBO.getCheckInDate());
        orders.setCheckOutDate(orderBO.getCheckOutDate());
        orders.setGuestPhone(orderBO.getGuestPhone());
        orders.setHousekeeperId(orderBO.getHousekeeperId());
        orders.setIncome(orderBO.getIncome());
        orders.setSourceId(orderBO.getSourceId());
        orders.setRemarks(orderBO.getRemarks());
        orderService.updateOrder(orders);
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("detail","修改订单信息成功！");
        return  result.toJSONString();

    }

    //退房
    @RequestMapping(value = "checkOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  checkOut(@RequestBody OrderBO orderBO){
        int orderId = orderBO.getOrderId();
        Orders orders = orderService.queryOrdersByOrderId(orderId);
        Float income = orders.getIncome();
        Float returnMoney = orderBO.getReturnMoney();
        Float income_ = income-returnMoney;
        orders.setIncome(income_);
        //设置订单状态为0：已完成
        orders.setOrderStatus(0);
        orderService.updateOrder(orders);
        //设置房间状态为0：空闲
        roomService.updateRoomStatusByRoomId(orders.getRoomId(),0);
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("detail","退房成功！");
        return  result.toJSONString();
    }

    //删除订单
    @RequestMapping(value = "deleteOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  deleteOrder(@RequestBody OrderBO orderBO){
        int orderId = orderBO.getOrderId();
        //删除订单，判断订单是否已完成
        if (orderService.queryByOrderId(orderId).getOrderStatus()==0){  //如果该订单之前已经完成了
            // ，说明房间状态已经为应该的样子，不需要置房间状态
            orderService.updateOrderStatus(orderId,2);
        }else { //该订单还没完成，删除订单后要置该房间为空闲状态
            roomService.updateRoomStatusByRoomId(orderService.queryByOrderId(orderId).getRoomId(),0);
            orderService.updateOrderStatus(orderId,2);
        }
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("detail","删除订单信息成功！");
        return  result.toJSONString();
    }




}
