package anonor.pojo;

import javax.persistence.*;

public class Housekeeper {
    @Id
    @Column(name = "housekeeper_id")
    private Integer housekeeperId;

    private String name;

    private Integer age;

    private Float wage;

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
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return wage
     */
    public Float getWage() {
        return wage;
    }

    /**
     * @param wage
     */
    public void setWage(Float wage) {
        this.wage = wage;
    }
}