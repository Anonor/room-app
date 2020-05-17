package room.pojo.vo;

//民宿房屋分组信息VO
public class RoomGroupVO {

    /**
     * 房间分组Id
     */
    private Integer groupId;

    /**
     * 所属民宿id
     */
    private Integer pensionId;

    /**
     * 分组名称
     */
    private String groupName;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPensionId() {
        return pensionId;
    }

    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
