package room.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Housekeeper {
    /**
     * 保洁人员id
     */
    @Id
    @Column(name = "housekeeper_id")
    private Integer housekeeperId;

    /**
     * 所属民宿id
     */
    @Column(name = "pension_id")
    private Integer pensionId;

    /**
     * 保洁人员姓名
     */
    private String name;

    /**
     * 保洁人员电话
     */
    private String phone;

    /**
     * 保洁人员每次工资
     */
    private Float wage;

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
     * 获取所属民宿id
     *
     * @return pension_id - 所属民宿id
     */
    public Integer getPensionId() {
        return pensionId;
    }

    /**
     * 设置所属民宿id
     *
     * @param pensionId 所属民宿id
     */
    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
    }

    /**
     * 获取保洁人员姓名
     *
     * @return name - 保洁人员姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置保洁人员姓名
     *
     * @param name 保洁人员姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取保洁人员电话
     *
     * @return phone - 保洁人员电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置保洁人员电话
     *
     * @param phone 保洁人员电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取保洁人员每次工资
     *
     * @return wage - 保洁人员每次工资
     */
    public Float getWage() {
        return wage;
    }

    /**
     * 设置保洁人员每次工资
     *
     * @param wage 保洁人员每次工资
     */
    public void setWage(Float wage) {
        this.wage = wage;
    }
}