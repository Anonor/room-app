package room.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Room {
    /**
     * 房间id
     */
    @Id
    @Column(name = "room_id")
    private Integer roomId;

    /**
     * 所属民宿id
     */
    @Column(name = "pension_id")
    private Integer pensionId;

    /**
     * 父房间id   0：房间组
     */
    @Column(name = "father_id")
    private Integer fatherId;

    /**
     * 房间名称
     */
    private String name;

    /**
     * 房间价格
     */
    private Float price;

    /**
     * 房间状态   0：空闲   1：入住   2：关闭
     */
    @Column(name = "room_status")
    private Integer roomStatus;

    /**
     * 获取房间id
     *
     * @return room_id - 房间id
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * 设置房间id
     *
     * @param roomId 房间id
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * 获取所属民宿id
     *
     * @return pension_id - 所属民宿id
     */
    public Integer getPensionId() {
        return pensionId;
    }

    /**
     * 设置所属民宿id
     *
     * @param pensionId 所属民宿id
     */
    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
    }

    /**
     * 获取父房间id   0：房间组
     *
     * @return father_id - 父房间id   0：房间组
     */
    public Integer getFatherId() {
        return fatherId;
    }

    /**
     * 设置父房间id   0：房间组
     *
     * @param fatherId 父房间id   0：房间组
     */
    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    /**
     * 获取房间名称
     *
     * @return name - 房间名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置房间名称
     *
     * @param name 房间名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取房间价格
     *
     * @return price - 房间价格
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置房间价格
     *
     * @param price 房间价格
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 获取房间状态   0：空闲   1：入住   2：关闭
     *
     * @return room_status - 房间状态   0：空闲   1：入住   2：关闭
     */
    public Integer getRoomStatus() {
        return roomStatus;
    }

    /**
     * 设置房间状态   0：空闲   1：入住   2：关闭
     *
     * @param roomStatus 房间状态   0：空闲   1：入住   2：关闭
     */
    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }
}