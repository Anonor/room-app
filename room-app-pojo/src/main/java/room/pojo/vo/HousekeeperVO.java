package room.pojo.vo;

public class HousekeeperVO {
    /**
     * 保洁人员id
     */
    private Integer housekeeperId;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getWage() {
        return wage;
    }

    public void setWage(Float wage) {
        this.wage = wage;
    }

    public Integer getHousekeeperId() {
        return housekeeperId;
    }

    public void setHousekeeperId(Integer housekeeperId) {
        this.housekeeperId = housekeeperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
