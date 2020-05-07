package anonor.pojo;

import java.util.Date;
import javax.persistence.*;

public class Order {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "merchant_id")
    private Integer merchantId;

    @Column(name = "pension_id")
    private Integer pensionId;

    private Date date;

    @Column(name = "housekeeper_id")
    private Integer housekeeperId;

    @Column(name = "check_in_date")
    private Date checkInDate;

    @Column(name = "check_out_date")
    private Date checkOutDate;

    /**
     * 订单价格（包括保洁费用和民宿费用）
     */
    private Float expense;

    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return merchant_id
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return pension_id
     */
    public Integer getPensionId() {
        return pensionId;
    }

    /**
     * @param pensionId
     */
    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
    }

    /**
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return housekeeper_id
     */
    public Integer getHousekeeperId() {
        return housekeeperId;
    }

    /**
     * @param housekeeperId
     */
    public void setHousekeeperId(Integer housekeeperId) {
        this.housekeeperId = housekeeperId;
    }

    /**
     * @return check_in_date
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * @param checkInDate
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * @return check_out_date
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * @param checkOutDate
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * 获取订单价格（包括保洁费用和民宿费用）
     *
     * @return expense - 订单价格（包括保洁费用和民宿费用）
     */
    public Float getExpense() {
        return expense;
    }

    /**
     * 设置订单价格（包括保洁费用和民宿费用）
     *
     * @param expense 订单价格（包括保洁费用和民宿费用）
     */
    public void setExpense(Float expense) {
        this.expense = expense;
    }
}