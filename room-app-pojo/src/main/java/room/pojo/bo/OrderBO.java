package room.pojo.bo;

import java.util.Date;

public class OrderBO {

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单商家id
     */
    private Integer merchantId;

    private String sessionId;

    /**
     * 订单民宿id
     */
    private Integer pensionId;

    /**
     * 客源id
     */
    private Integer sourceId;

    /**
     * 保洁人员id
     */
    private Integer housekeeperId;

    /**
     * 入住时间
     */
    private Date checkInDate;

    /**
     * 退房时间
     */
    private Date checkOutDate;

    /**
     * 房客姓名
     */
    private String guestName;

    /**
     * 房客手机号
     */
    private String guestPhone;

    /**
     * 订单收入
     */
    private Float income;

    /**
     * 订单状态   0：已退房   1：入住中
     */
    private Integer orderStatus;

    /**
     * 房客备注
     */
    private String remarks;

    /*
    * 该房客入住房间所属的组Id
    * */
    private int groupId;

    /*
    * 该房客入住房间的房间Id
    * */
    private int roomId;

    /*
    * 退房时，如果实离房时间比原离房时间早的退还金额
    * */
    private Float returnMoney;


    public Float getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(Float returnMoney) {
        this.returnMoney = returnMoney;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

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

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getHousekeeperId() {
        return housekeeperId;
    }

    public void setHousekeeperId(Integer housekeeperId) {
        this.housekeeperId = housekeeperId;
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
}
