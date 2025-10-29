public class MonthlyRecord {
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private int unitPrice;

    public MonthlyRecord(String itemName, boolean isExpense, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Геттеры
    public String getItemName() { return itemName; }
    public boolean isExpense() { return isExpense; }
    public int getQuantity() { return quantity; }
    public int getUnitPrice() { return unitPrice; }

    // Рассчитываем общую сумму для этой записи
    public int getTotal() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return "MonthlyRecord{" +
                "itemName='" + itemName + '\'' +
                ", isExpense=" + isExpense +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}