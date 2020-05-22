package room.pojo.vo;

//民宿营收VO
public class PensionIncomeVO {

    //支出
    private float pay;
    //收入
    private float income;
    //利润
    private float profit;

    public float getPay() {
        return pay;
    }

    public void setPay(float pay) {
        this.pay = pay;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "PensionIncomeVO{" +
                "pay=" + pay +
                ", income=" + income +
                ", profit=" + profit +
                '}';
    }
}
