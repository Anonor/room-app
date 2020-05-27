package room.pojo.bo;

import java.util.Date;

public class PensionBO {

    //民宿图片
    private String pensionImg;
    /*
    * seesionId
    * */
    private String sessionId;
    /*
    * 门店Id
    * */
    private Integer pensionId;

    /*
    * 组Id
    * */
    private Integer groupId;

    /**
     * 该民宿的商家id
     */
    private Integer merchantId;

    /*
    * 民宿名字
    * */
    private String pensionName;

    /**
     * 民宿状态
     */
    private Integer status;
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
    private String addressProvince;

    /**
     * 市
     */
    private String addressCity;

    /**
     * 区（县）
     */
    private String addressDistrict;

    /**
     * 详细地址
     */
    private String addressDetail;


    /*
    * 修改民宿信息的类型
    * 0：修改name
    * 1：修改其他信息
    * */
    private int type;

    /*
    * 所属商家账号
    * */
    private String account;

    /*
    * 所属商家品牌名
    * */
    private String brandName;

    /*
    * 民宿创建时间
    * */
    private String createTime;

    /*
    * 民宿更新时间
    * */
    private String updateTime;

    /*
    * 前端参数
    * 查询营收的开始时间
    * */
    private Date startDate;

    /*
    * 前端参数
    * 查询营收的结束时间
    * */
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getPensionImg() {
        return pensionImg;
    }

    public void setPensionImg(String pensionImg) {
        this.pensionImg = pensionImg;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getPensionId() {
        return pensionId;
    }

    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getPensionName() {
        return pensionName;
    }

    public void setPensionName(String pensionName) {
        this.pensionName = pensionName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
