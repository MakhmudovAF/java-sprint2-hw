import java.util.ArrayList;

public class ReportEngine {
    private MonthlyReport monthlyReport;
    private YearlyReport yearlyReport;
    private FileReader fileReader;
    private String[] monthName = {"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь", "Декабрь"};

    public ReportEngine() {
        this.fileReader = new FileReader();
        this.monthlyReport = new MonthlyReport(monthName);
        this.yearlyReport = new YearlyReport("2021", monthName);
    }

    public void readAllMonthlyReports() {
        System.out.println("\nЧтение всех месячных отчётов...");

        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        boolean allReadSuccessfully = true;

        for (String month : months) {
            String fileName = "m.2021" + month + ".csv";
            ArrayList<String> lines = fileReader.readFileContents(fileName);

            if (lines.isEmpty()) {
                System.out.println("Не удалось прочитать файл: " + fileName);
                allReadSuccessfully = false;
                continue;
            }

            // Пропускаем заголовок (первую строку)
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String itemName = parts[0].trim();
                    boolean isExpense = Boolean.parseBoolean(parts[1].trim());
                    int quantity = Integer.parseInt(parts[2].trim());
                    int unitPrice = Integer.parseInt(parts[3].trim());

                    MonthlyRecord record = new MonthlyRecord(itemName, isExpense, quantity, unitPrice);
                    monthlyReport.addRecord(month, record);
                }
            }

            System.out.println("Месячный отчёт за " + getMonthName(month) + " 2021 успешно считан.");
        }

        if (allReadSuccessfully) {
            System.out.println("Все месячные отчёты успешно загружены!");
        }
    }

    public void readYearlyReport() {
        System.out.println("\nЧтение годового отчёта...");
        String fileName = "y.2021.csv";
        ArrayList<String> lines = fileReader.readFileContents(fileName);

        if (lines.isEmpty()) {
            System.out.println("Не удалось прочитать файл: " + fileName);
            return;
        }

        // Пропускаем заголовок (первую строку)
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            if (parts.length == 3) {
                String month = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());
                boolean isExpense = Boolean.parseBoolean(parts[2].trim());

                YearlyRecord record = new YearlyRecord(month, amount, isExpense);
                yearlyReport.addRecord(record);
            }
        }

        System.out.println("Годовой отчёт за 2021 год успешно считан.");
    }

    public void reconcileReports() {
        if (monthlyReport.getAllData().isEmpty()) {
            System.out.println("Месячные отчёты не загружены. Пожалуйста, сначала выполните команду 1.");
            return;
        }

        if (yearlyReport.getRecords().isEmpty()) {
            System.out.println("Годовой отчёт не загружен. Пожалуйста, сначала выполните команду 2.");
            return;
        }

        System.out.println("\n=== СВЕРКА ОТЧЁТОВ ===");
        boolean hasErrors = false;

        for (String month : monthlyReport.getAllData().keySet()) {
            int monthlyIncome = monthlyReport.getTotalIncomeForMonth(month);
            int yearlyIncome = yearlyReport.getIncomeForMonth(month);

            int monthlyExpense = monthlyReport.getTotalExpenseForMonth(month);
            int yearlyExpense = yearlyReport.getExpenseForMonth(month);

            if (monthlyIncome != yearlyIncome) {
                System.out.println("Обнаружено несоответствие в доходах за " + getMonthName(month) + ":");
                System.out.println("  Месячный отчёт: " + monthlyIncome);
                System.out.println("  Годовой отчёт: " + yearlyIncome);
                hasErrors = true;
            }

            if (monthlyExpense != yearlyExpense) {
                System.out.println("Обнаружено несоответствие в расходах за " + getMonthName(month) + ":");
                System.out.println("  Месячный отчёт: " + monthlyExpense);
                System.out.println("  Годовой отчёт: " + yearlyExpense);
                hasErrors = true;
            }
        }

        if (!hasErrors) {
            System.out.println("Сверка завершена успешно! Несоответствий не обнаружено.");
        }
    }

    public void printMonthlyReportsInfo() {
        if (monthlyReport.getAllData().isEmpty()) {
            System.out.println("Месячные отчёты не загружены. Пожалуйста, сначала выполните команду 1.");
            return;
        }

        System.out.println("\n=== ИНФОРМАЦИЯ ПО МЕСЯЧНЫМ ОТЧЁТАМ ===");
        monthlyReport.printInfo();
    }

    public void printYearlyReportInfo() {
        if (yearlyReport.getRecords().isEmpty()) {
            System.out.println("Годовой отчёт не загружен. Пожалуйста, сначала выполните команду 2.");
            return;
        }

        System.out.println("\n=== ИНФОРМАЦИЯ ПО ГОДОВОМУ ОТЧЁТУ ===");
        yearlyReport.printInfo();
    }

    private String getMonthName(String monthNumber) {
        return monthName[Integer.parseInt(monthNumber) - 1];
    }
}