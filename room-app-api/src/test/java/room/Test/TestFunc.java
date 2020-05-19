package room.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import room.common.utils.DateUtils;
import room.pojo.Room;
import room.pojo.vo.RoomGroupVO;
import room.pojo.vo.RoomVO;
import room.service.IndexService;
import room.service.OrderService;
import room.service.PensionService;
import room.service.RoomService;
import room.service.impl.IndexServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    private IndexService indexService;

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
        roomService.updateRoomStatusByRoomId(31, 2);
        Room room = new Room();
        room.setName("房间3-2");
        room.setFatherId(13);
        room.setPensionId(7);
        roomService.createRoom(room);
    }

    @Test
    public void queryValidRoomGroup() {
//        System.out.println(roomService.queryRoomGroupsByPensionId(7));
        System.out.println(roomService.queryValidRoomGroupsByPensionId(1));
    }

    @Test
    public void testDays() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2020-05-07");
        Date endDate = sdf.parse("2020-05-07");
        System.out.println(DateUtils.getDays(startDate, endDate));
    }

    @Test
    public void testPensionIncome() {
        System.out.println(indexService.queryPensionIncome(7));
    }

    @Test
    public void testRoomIncome() {
        System.out.println(indexService.queryRoomIncomesByPensionId(7));
    }

    @Test
    public void testRate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2020-05-08");
        Date endDate = sdf.parse("2020-05-19");
        float result = indexService.queryOccupancyRateByRoomIc(35, startDate, endDate);
        System.out.println(result);
    }
}
