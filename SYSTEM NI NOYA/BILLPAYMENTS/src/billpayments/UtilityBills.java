package billpayments;

import java.util.Scanner;

public class UtilityBills {

    public void addBills() {
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();
        
        System.out.println("=====================================");
        System.out.println("|         ADD UTILITY BILL         |");
        System.out.println("=====================================");
        
        System.out.print("Enter Type of Bills: ");
        String nbill = sc.next();
        System.out.print("Enter Name Of Company: ");
        String com = sc.next();
        System.out.print("Payment Method: ");
        String met = sc.next();
        System.out.print("Release Bill Date Every Month (e.g., 8-9): ");
        String date = sc.next();
        System.out.print("Due Date (e.g., 20-23): ");
        String due = sc.next();
        
        String sql = "INSERT INTO UtilityBills (Type, Name_Of_Company, Payment_Method, Release_Date, Due_Date) VALUES (?,?,?,?,?)";
        
        conf.addRecord(sql, nbill, com, met, date, due);
        System.out.println("Utility bill added successfully!");
    }

    public void viewBills() {
        String query = "SELECT * FROM UtilityBills";
        String[] headers = {"ID", "Type", "Company", "Payment Method", "Release Date", "Due Date"};
        String[] columns = {"UB_ID", "Type", "Name_Of_Company", "Payment_Method", "Release_Date", "Due_Date"};

        CONFIG conf = new CONFIG();
        conf.viewRecords(query, headers, columns);
    }

    public void mainBills() {
        CONFIG conf = new CONFIG();
        UtilityBills ub = new UtilityBills();
        Scanner sc = new Scanner(System.in);
        
        String res;
        do {
            System.out.println("=====================================");
            System.out.println("|         UTILITY BILL MENU        |");
            System.out.println("=====================================");
            System.out.println("1. Add Bills");
            System.out.println("2. View Bills");
            System.out.println("3. Update Bills");
            System.out.println("4. Delete Bills");
            System.out.println("5. Exit");

            int choice;
            while (true) {
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 5) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 5.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next();
                }
            }

            switch (choice) {
                case 1:
                    ub.addBills();
                    break;
                case 2:
                    ub.viewBills();
                    break;
                case 3:
                    ub.viewBills();
                    String sqlUpdate = "UPDATE UtilityBills SET Payment_Method = ?, Release_Date = ?, Due_Date = ? WHERE UB_ID = ?";

                    int ID2;
                    while (true) {
                        System.out.print("Enter Utility Bill ID to update: ");
                        if (sc.hasNextInt()) {
                            ID2 = sc.nextInt();
                            if (conf.getSingleValues("SELECT Ub_ID FROM UtilityBills WHERE Ub_ID = ?", ID2) != 0) {
                                break;
                            } else {
                                System.out.println("Selected Utility Bill doesn't exist.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid numeric Utility Bill ID.");
                            sc.next();
                        }
                    }

                    System.out.print("Enter new Payment method: ");
                    String newpay = sc.next();
                    System.out.print("Enter new Release Date: ");
                    String newdate = sc.next();
                    System.out.print("Enter new Due Date: ");
                    String newdue = sc.next();

                    conf.updateRecord(sqlUpdate, newpay, newdate, newdue, ID2);
                    System.out.println("Utility bill updated successfully!");
                    break;

                case 4:
                    ub.viewBills();
                    String sqlDEL = "DELETE FROM UtilityBills WHERE UB_ID = ?";
                    int idUp;
                    while (true) {
                        System.out.print("Enter Utility Bill ID: ");
                        if (sc.hasNextInt()) {
                            idUp = sc.nextInt();
                            if (conf.getSingleValues("SELECT UB_ID FROM UtilityBills WHERE UB_ID = ?", idUp) != 0) {
                                break;
                            } else {
                                System.out.println("Selected Utility Bill doesn't exist.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid numeric Utility Bill ID.");
                            sc.next();
                        }
                    }
                    conf.deleteRecord(sqlDEL, idUp);
                    System.out.println("Utility bill deleted successfully!");
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;
            }
            System.out.println("");
            System.out.print("Do you want to continue? Yes or No: ");
            res = sc.next();
            
        } while (res.equalsIgnoreCase("yes"));
    }
}
