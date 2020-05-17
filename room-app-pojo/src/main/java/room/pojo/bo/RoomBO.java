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
     * 房间状态   0：空闲   1：入住   2：关闭
     */
    private Integer roomStatus;

    /*
    * 前端参数，根据状态及组id查询房间列表
    * 0:查询所有，1：查询空闲房间，2：查询入住房间
    * */
    private int status;

    /*
    * 前端参数：根据类型选择修改房间信息
    * 0：只修改房间名称；1：移动了分组（包括只移动分组和即移动分组又修改名字）
    * */
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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
