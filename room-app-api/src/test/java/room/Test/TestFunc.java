package room.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import room.pojo.Room;
import room.pojo.vo.RoomGroupVO;
import room.pojo.vo.RoomVO;
import room.service.OrderService;
import room.service.PensionService;
import room.service.RoomService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFunc {

    @Autowired
    private PensionService pensionService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private OrderService orderService;

    @Test
    public void isPensionExist() {
        boolean result = pensionService.isPensionExist(32, "鼎济大酒店");
        System.out.println(result);
    }

    @Test
    public void getValidRoomGroup() {
        List<RoomGroupVO> list = roomService.queryRoomGroupsByPensionId(1);
        System.out.println(list);
    }

    @Test
    public void getRoomInfo() {
        RoomVO roomVO = roomService.queryRoomInfoByRoomId(2);
        System.out.println(roomVO.getRoomName() + " " + roomVO.getGroupName());
    }

    @Test
    public void getOrderVO() {
        System.out.println(orderService.queryByOrderId(1));
    }

    @Test
    public void getOrderVOs() {
        System.out.println(orderService.queryByPensionIdAndOrderStatus(1, 1));
    }

    @Test
    public void getOrderVOs2() {
        System.out.println(orderService.queryByRoomIdAndOrderStatus(2, 0));
    }

    @Test
    public void addRoomGroup() {
        Room room = new Room();
        room.setName("qwerty");
        room.setPensionId(1);
        roomService.createRoomGroup(room);
    }

    @Test
    public void queryValidRoomGroup() {
//        System.out.println(roomService.queryRoomGroupsByPensionId(7));
        System.out.println(roomService.queryValidRoomGroupsByPensionId(1));
    }

}
