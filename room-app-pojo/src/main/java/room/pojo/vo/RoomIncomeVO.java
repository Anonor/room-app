package room.pojo.vo;

//房间营收VO
public class RoomIncomeVO {

    private Integer roomId;
    private String roomName;
    private Integer days;
    private float income;
    private float occupancyRate;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getOccupancyRate() {
        return occupancyRate;
    }

    public void setOccupancyRate(float occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    @Override
    public String toString() {
        return "RoomIncomeVO{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", days=" + days +
                ", income=" + income +
                ", occupancyRate=" + occupancyRate +
                '}';
    }
}
