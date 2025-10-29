public class YearlyRecord {
    private String month;
    private int amount;
    private boolean isExpense;

    public YearlyRecord(String month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    // Геттеры
    public String getMonth() { return month; }
    public int getAmount() { return amount; }
    public boolean isExpense() { return isExpense; }

    @Override
    public String toString() {
        return "YearlyRecord{" +
                "month='" + month + '\'' +
                ", amount=" + amount +
                ", isExpense=" + isExpense +
                '}';
    }
}