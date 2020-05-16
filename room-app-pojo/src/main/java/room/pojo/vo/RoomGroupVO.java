package room.pojo.vo;

import room.pojo.Room;

import java.util.List;

//民宿房屋分组信息VO
public class RoomGroupVO {

    /**
     * 房间Id
     */
    private Integer roomId;

    /**
     * 所属民宿id
     */
    private Integer pensionId;

    /**
     * 房间（分组）名称
     */
    private String name;

    /*
    * 房间（分组）状态 0：不可用；1：可用
    * */
    private Integer roomStatus;

    /**
     * 分组下房间信息
     */
    private List<Room> roomList;

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getPensionId() {
        return pensionId;
    }

    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
