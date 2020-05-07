package room.pojo;

import javax.persistence.*;

public class Merchant {
    @Id
    @Column(name = "merchant_id")
    private Integer merchantId;

    private Integer account;

    private String password;

    @Column(name = "brand_name")
    private String brandName;

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
     * @return account
     */
    public Integer getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(Integer account) {
        this.account = account;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return brand_name
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}