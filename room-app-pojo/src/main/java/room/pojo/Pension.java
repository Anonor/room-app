package room.pojo;

import javax.persistence.*;

public class Pension {
    @Id
    @Column(name = "pension_id")
    private Integer pensionId;

    /**
     * 该民宿的商家id
     */
    @Column(name = "merchant_id")
    private Integer merchantId;

    private String name;

    /**
     * 民宿一天的价格
     */
    private Float price;

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
     * 获取该民宿的商家id
     *
     * @return merchant_id - 该民宿的商家id
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * 设置该民宿的商家id
     *
     * @param merchantId 该民宿的商家id
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取民宿一天的价格
     *
     * @return price - 民宿一天的价格
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置民宿一天的价格
     *
     * @param price 民宿一天的价格
     */
    public void setPrice(Float price) {
        this.price = price;
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
}