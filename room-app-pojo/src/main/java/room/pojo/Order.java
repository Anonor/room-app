package room.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Order {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单商家id
     */
    @Column(name = "merchant_id")
    private Integer merchantId;

    /**
     * 订单民宿id
     */
    @Column(name = "pension_id")
    private Integer pensionId;

    /**
     * 客源id
     */
    @Column(name = "source_id")
    private Integer sourceId;

    /**
     * 保洁人员id
     */
    @Column(name = "housekeeper_id")
    private Integer housekeeperId;

    /**
     * 入住时间
     */
    @Column(name = "check_in_date")
    private Date checkInDate;

    /**
     * 退房时间
     */
    @Column(name = "check_out_date")
    private Date checkOutDate;

    /**
     * 房客姓名
     */
    @Column(name = "guest_name")
    private String guestName;

    /**
     * 房客手机号
     */
    @Column(name = "guest_phone")
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
     * 订单状态   0：已退房   1：入住中
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单商家id
     *
     * @return merchant_id - 订单商家id
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * 设置订单商家id
     *
     * @param merchantId 订单商家id
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取订单民宿id
     *
     * @return pension_id - 订单民宿id
     */
    public Integer getPensionId() {
        return pensionId;
    }

    /**
     * 设置订单民宿id
     *
     * @param pensionId 订单民宿id
     */
    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
    }

    /**
     * 获取客源id
     *
     * @return source_id - 客源id
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * 设置客源id
     *
     * @param sourceId 客源id
     */
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获取保洁人员id
     *
     * @return housekeeper_id - 保洁人员id
     */
    public Integer getHousekeeperId() {
        return housekeeperId;
    }

    /**
     * 设置保洁人员id
     *
     * @param housekeeperId 保洁人员id
     */
    public void setHousekeeperId(Integer housekeeperId) {
        this.housekeeperId = housekeeperId;
    }

    /**
     * 获取入住时间
     *
     * @return check_in_date - 入住时间
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * 设置入住时间
     *
     * @param checkInDate 入住时间
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * 获取退房时间
     *
     * @return check_out_date - 退房时间
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * 设置退房时间
     *
     * @param checkOutDate 退房时间
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * 获取房客姓名
     *
     * @return guest_name - 房客姓名
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * 设置房客姓名
     *
     * @param guestName 房客姓名
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * 获取房客手机号
     *
     * @return guest_phone - 房客手机号
     */
    public String getGuestPhone() {
        return guestPhone;
    }

    /**
     * 设置房客手机号
     *
     * @param guestPhone 房客手机号
     */
    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    /**
     * 获取房客备注
     *
     * @return remarks - 房客备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置房客备注
     *
     * @param remarks 房客备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取订单收入
     *
     * @return income - 订单收入
     */
    public Float getIncome() {
        return income;
    }

    /**
     * 设置订单收入
     *
     * @param income 订单收入
     */
    public void setIncome(Float income) {
        this.income = income;
    }

    /**
     * 获取订单状态   0：已退房   1：入住中
     *
     * @return order_status - 订单状态   0：已退房   1：入住中
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态   0：已退房   1：入住中
     *
     * @param orderStatus 订单状态   0：已退房   1：入住中
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}