package room.pojo.vo;

//民宿房屋分组信息VO
public class RoomGroupVO {

    /**
     * id
     */
    private Integer roomId;

    /**
     * 所属民宿id
     */
    private Integer pensionId;


    /**
     * 分组名称
     */
    private String name;

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
