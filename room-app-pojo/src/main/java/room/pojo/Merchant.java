package room.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Merchant {
    /**
     * 商家id
     */
    @Id
    @Column(name = "merchant_id")
    private Integer merchantId;

    /**
     * 商家账号（邮箱）
     */
    private String account;

    /**
     * 商家密码
     */
    private String password;

    /**
     * 商家名称
     */
    @Column(name = "brand_name")
    private String brandName;

    /**
     * 商家状态   0：禁用   1：正常
     */
    @Column(name = "merchant_status")
    private Integer merchantStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取商家id
     *
     * @return merchant_id - 商家id
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商家id
     *
     * @param merchantId 商家id
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取商家账号（邮箱）
     *
     * @return account - 商家账号（邮箱）
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置商家账号（邮箱）
     *
     * @param account 商家账号（邮箱）
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取商家密码
     *
     * @return password - 商家密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置商家密码
     *
     * @param password 商家密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取商家名称
     *
     * @return brand_name - 商家名称
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置商家名称
     *
     * @param brandName 商家名称
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获取商家状态   0：禁用   1：正常
     *
     * @return merchant_status - 商家状态   0：禁用   1：正常
     */
    public Integer getMerchantStatus() {
        return merchantStatus;
    }

    /**
     * 设置商家状态   0：禁用   1：正常
     *
     * @param merchantStatus 商家状态   0：禁用   1：正常
     */
    public void setMerchantStatus(Integer merchantStatus) {
        this.merchantStatus = merchantStatus;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}