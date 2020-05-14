package room.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Pension {
    /**
     * 民宿id
     */
    @Id
    @Column(name = "pension_id")
    private Integer pensionId;

    /**
     * 民宿的商家id
     */
    @Column(name = "merchant_id")
    private Integer merchantId;

    /**
     * 民宿名称
     */
    private String name;

    /**
     * 民宿图片地址
     */
    private String image;

    /**
     * 民宿状态  0：禁用   1：正常   2：关闭
     */
    @Column(name = "pension_status")
    private Integer pensionStatus;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 维度
     */
    private String latitude;

    /**
     * 省
     */
    @Column(name = "address_province")
    private String addressProvince;

    /**
     * 市
     */
    @Column(name = "address_city")
    private String addressCity;

    /**
     * 区（县）
     */
    @Column(name = "address_district")
    private String addressDistrict;

    /**
     * 详细地址
     */
    @Column(name = "address_detail")
    private String addressDetail;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取民宿id
     *
     * @return pension_id - 民宿id
     */
    public Integer getPensionId() {
        return pensionId;
    }

    /**
     * 设置民宿id
     *
     * @param pensionId 民宿id
     */
    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
    }

    /**
     * 获取民宿的商家id
     *
     * @return merchant_id - 民宿的商家id
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * 设置民宿的商家id
     *
     * @param merchantId 民宿的商家id
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取民宿名称
     *
     * @return name - 民宿名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置民宿图片地址
     *
     * @param image 民宿图片地址
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取民宿图片地址
     *
     * @return image - 民宿图片地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置民宿名称
     *
     * @param name 民宿名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取民宿状态
     *
     * @return pension_status - 民宿状态
     */
    public Integer getPensionStatus() {
        return pensionStatus;
    }

    /**
     * 设置民宿状态
     *
     * @param pensionStatus 民宿状态
     */
    public void setPensionStatus(Integer pensionStatus) {
        this.pensionStatus = pensionStatus;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取维度
     *
     * @return latitude - 维度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置维度
     *
     * @param latitude 维度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取省
     *
     * @return address_province - 省
     */
    public String getAddressProvince() {
        return addressProvince;
    }

    /**
     * 设置省
     *
     * @param addressProvince 省
     */
    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    /**
     * 获取市
     *
     * @return address_city - 市
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * 设置市
     *
     * @param addressCity 市
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     * 获取区（县）
     *
     * @return address_district - 区（县）
     */
    public String getAddressDistrict() {
        return addressDistrict;
    }

    /**
     * 设置区（县）
     *
     * @param addressDistrict 区（县）
     */
    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    /**
     * 获取详细地址
     *
     * @return address_detail - 详细地址
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * 设置详细地址
     *
     * @param addressDetail 详细地址
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}