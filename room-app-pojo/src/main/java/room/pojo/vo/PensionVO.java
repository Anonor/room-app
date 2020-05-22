package room.pojo.vo;

public class PensionVO {
    /**
     * 民宿id
     */
    private Integer pensionId;


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
     * 民宿地址（省区县+详细位置）
     */
    private String address;

    /*
    * 省市区
    * */
    private String location;

    /*
    * 详细地址（address-location)
    * */
    private String detail;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPensionStatus() {
        return pensionStatus;
    }

    public void setPensionStatus(Integer pensionStatus) {
        this.pensionStatus = pensionStatus;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
