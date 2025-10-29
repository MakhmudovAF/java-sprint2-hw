import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportEngine reportEngine = new ReportEngine();

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                reportEngine.readAllMonthlyReports();
            } else if (command == 2) {
                reportEngine.readYearlyReport();
            } else if (command == 3) {
                reportEngine.reconcileReports();
            } else if (command == 4) {
                reportEngine.printMonthlyReportsInfo();
            } else if (command == 5) {
                reportEngine.printYearlyReportInfo();
            } else if (command == 0) {
                System.out.println("Выход из приложения. До свидания!");
                scanner.close();
                return;
            } else {
                System.out.println("Неизвестная команда. Пожалуйста, выберите команду из меню.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
        System.out.print("Выберите команду: ");
    }
}