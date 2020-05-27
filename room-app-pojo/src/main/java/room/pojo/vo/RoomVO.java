package room.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Id;

public class RoomVO {
    /**
     * 房间id
     */
    private Integer roomId;

    /**
     * 所属民宿id
     */
    private Integer pensionId;

    /**
     * 分组id
     */
    private Integer groupId;

    /**
     * 分组name
     */
    private String groupName;

    /**
     * 房间名称
     */
    private String roomName;

    /**
     * 房间价格
     */
    private Float price;

    /**
     * 房间状态   0：空闲   1：入住
     */
    private Integer roomStatus;

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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public String toString() {
        return "RoomVO{" +
                "roomId=" + roomId +
                ", pensionId=" + pensionId +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", price=" + price +
                ", roomStatus=" + roomStatus +
                '}';
    }
}