package room.pojo.vo;

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
     * 名称
     */
    private String name;

    /*
    * 分组Id
    * */
    private int groupId;

    /*
    * 分组name
    * */
    private String groupName;


    /*
    * 分组下的房间name
    * */
    private String roomName;


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
}
