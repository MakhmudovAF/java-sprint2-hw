import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    private String[] monthName;
    private String year;
    private ArrayList<YearlyRecord> records;

    public YearlyReport(String year, String[] monthName) {
        this.monthName = monthName;
        this.year = year;
        this.records = new ArrayList<>();
    }

    public void addRecord(YearlyRecord record) {
        records.add(record);
    }

    public ArrayList<YearlyRecord> getRecords() {
        return records;
    }

    public int getIncomeForMonth(String month) {
        int total = 0;

        for (YearlyRecord record : records) {
            if (record.getMonth().equals(month) && !record.isExpense()) {
                total += record.getAmount();
            }
        }
        return total;
    }

    public int getExpenseForMonth(String month) {
        int total = 0;

        for (YearlyRecord record : records) {
            if (record.getMonth().equals(month) && record.isExpense()) {
                total += record.getAmount();
            }
        }
        return total;
    }

    public void printInfo() {
        if (records.isEmpty()) {
            System.out.println("Нет данных годового отчёта.");
            return;
        }

        System.out.println("Отчёт за " + year + " год:");

        HashMap<String, Integer> incomeByMonth = new HashMap<>();
        HashMap<String, Integer> expenseByMonth = new HashMap<>();

        for (YearlyRecord record : records) {
            String month = record.getMonth();

            if (record.isExpense()) {
                expenseByMonth.put(month, expenseByMonth.getOrDefault(month, 0) + record.getAmount());
            } else {
                incomeByMonth.put(month, incomeByMonth.getOrDefault(month, 0) + record.getAmount());
            }
        }

        double totalIncome = 0;
        double totalExpense = 0;
        int monthCount = 0;

        for (String month : incomeByMonth.keySet()) {
            int income = incomeByMonth.get(month);
            int expense = expenseByMonth.getOrDefault(month, 0);
            int profit = income - expense;

            System.out.println("  Прибыль за " + getMonthName(month) + ": " + profit);

            totalIncome += income;
            totalExpense += expense;
            monthCount++;
        }

        if (monthCount > 0) {
            System.out.println("  Средний доход: " + String.format("%.2f", totalIncome / monthCount));
            System.out.println("  Средний расход: " + String.format("%.2f", totalExpense / monthCount));
        }
    }

    private String getMonthName(String monthNumber) {
        return monthName[Integer.parseInt(monthNumber) - 1];
    }
}