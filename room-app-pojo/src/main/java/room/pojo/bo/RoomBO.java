package room.pojo.bo;

public class RoomBO {

    /**
     * 房间id
     */
    private Integer roomId;

    /**
     * 所属民宿id
     */
    private Integer pensionId;

    /**
     * 父房间id   0：房间组
     */
    private Integer fatherId;

    /**
     * 房间名称
     */
    private String roomName;

    /*
    * 分组名称
    * */
    private String groupName;

    /*
    * 分组id
    * */
    private int groupId;

    /**
     * 房间价格
     */
    private Float price;

    /**
     * 房间状态   0：空闲   1：入住
     */
    private Integer roomStatus;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
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
}
