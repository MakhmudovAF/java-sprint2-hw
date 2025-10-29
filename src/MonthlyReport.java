import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    private String[] monthName;
    private HashMap<String, ArrayList<MonthlyRecord>> monthlyData;

    public MonthlyReport(String[] monthName) {
        this.monthName = monthName;
        this.monthlyData = new HashMap<>();
    }

    public void addRecord(String month, MonthlyRecord record) {
        if (!monthlyData.containsKey(month)) {
            monthlyData.put(month, new ArrayList<>());
        }
        monthlyData.get(month).add(record);
    }

    public HashMap<String, ArrayList<MonthlyRecord>> getAllData() {
        return monthlyData;
    }

    public int getTotalIncomeForMonth(String month) {
        int total = 0;

        for (MonthlyRecord monthlyRecord : monthlyData.get(month)) {
            if (!monthlyRecord.isExpense()) {
                total += monthlyRecord.getTotal();
            }
        }

        return total;
    }

    public int getTotalExpenseForMonth(String month) {
        int total = 0;

        for (MonthlyRecord monthlyRecord : monthlyData.get(month)) {
            if (monthlyRecord.isExpense()) {
                total += monthlyRecord.getTotal();
            }
        }

        return total;
    }

    public void printInfo() {
        if (monthlyData.isEmpty()) {
            System.out.println("Нет данных месячных отчётов.");
            return;
        }

        for (String month : monthlyData.keySet()) {
            System.out.println("\nМесяц: " + getMonthName(month));
            ArrayList<MonthlyRecord> records = monthlyData.get(month);

            int totalIncome = 0;
            int totalExpense = 0;
            String mostProfitableItem = "";
            int maxProfit = 0;
            String biggestExpenseItem = "";
            int maxExpense = 0;

            for (MonthlyRecord record : records) {
                int total = record.getTotal();

                if (record.isExpense()) {
                    totalExpense += total;

                    if (total > maxExpense) {
                        maxExpense = total;
                        biggestExpenseItem = record.getItemName();
                    }
                } else {
                    totalIncome += total;

                    if (total > maxProfit) {
                        maxProfit = total;
                        mostProfitableItem = record.getItemName();
                    }
                }
            }

            System.out.println("  Общий доход: " + totalIncome);
            System.out.println("  Общий расход: " + totalExpense);
            System.out.println("  Самый прибыльный товар: " + mostProfitableItem + " (" + maxProfit + ")");
            System.out.println("  Самая большая трата: " + biggestExpenseItem + " (" + maxExpense + ")");
        }
    }

    private String getMonthName(String monthNumber) {
        return monthName[Integer.parseInt(monthNumber) - 1];
    }
}