package room.pojo.vo;

import java.util.Date;

public class OrderVO {
    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单商家id
     */
    private Integer merchantId;

    /**
     * 订单民宿id
     */
    private Integer pensionId;

    /**
     * 订单房间id
     */
    private Integer roomId;

    /**
     * 订单房间name
     */
    private String roomName;

    /**
     * 订单房间分组id
     */
    private Integer groupId;

    /**
     * 订单房间分组name
     */
    private String groupName;

    /**
     * 客源id
     */
    private Integer sourceId;

    /**
     * 客源name
     */
    private String sourceName;

    /**
     * 保洁人员id
     */
    private Integer housekeeperId;

    /**
     * 保洁人员name
     */
    private String housekeeperName;

    /**
     * 入住时间
     */
    private Date checkInDate;

    /**
     * 退房时间
     */
    private Date  checkOutDate;

    /**
     * 入住时间
     */
    private String checkInDate_;

    /**
     * 退房时间
     */
    private String  checkOutDate_;

    /**
     * 房客姓名
     */
    private String guestName;

    /**
     * 房客手机号
     */
    private String guestPhone;

    /**
     * 房客备注
     */
    private String remarks;

    /**
     * 订单收入
     */
    private Float income;

    /**
     * 订单状态   0：已完成   1：待退房   2：订单无效
     */
    private Integer orderStatus;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getPensionId() {
        return pensionId;
    }

    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getHousekeeperId() {
        return housekeeperId;
    }

    public void setHousekeeperId(Integer housekeeperId) {
        this.housekeeperId = housekeeperId;
    }

    public String getHousekeeperName() {
        return housekeeperName;
    }

    public void setHousekeeperName(String housekeeperName) {
        this.housekeeperName = housekeeperName;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getCheckInDate_() {
        return checkInDate_;
    }

    public void setCheckInDate_(String checkInDate_) {
        this.checkInDate_ = checkInDate_;
    }

    public String getCheckOutDate_() {
        return checkOutDate_;
    }

    public void setCheckOutDate_(String checkOutDate_) {
        this.checkOutDate_ = checkOutDate_;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "orderId=" + orderId +
                ", merchantId=" + merchantId +
                ", pensionId=" + pensionId +
                ", roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", sourceId=" + sourceId +
                ", sourceName='" + sourceName + '\'' +
                ", housekeeperId=" + housekeeperId +
                ", housekeeperName='" + housekeeperName + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", guestName='" + guestName + '\'' +
                ", guestPhone='" + guestPhone + '\'' +
                ", remarks='" + remarks + '\'' +
                ", income=" + income +
                ", orderStatus=" + orderStatus +
                '}';
    }
}