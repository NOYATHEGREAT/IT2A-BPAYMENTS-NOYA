package billpayments;

import java.util.Scanner;

public class BillRecord {

    public void addBillRecord() {
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();

        System.out.println("=====================================");
        System.out.println("|          ADD BILL RECORD          |");
        System.out.println("=====================================");
        
        // View available users and bills
        USER us = new USER();
        us.viewUser();
        
        System.out.println(" - Select a Utility Bill - ");
        UtilityBills ub = new UtilityBills();
        ub.viewBills();

        // Get User ID
        int userId = getValidUserId(sc, conf);
        // Get Utility Bill ID
        int billId = getValidBillId(sc, conf);

        // Get payment details
        System.out.print("Enter Date you Paid (YYYY-MM-DD): ");
        String datePaid = sc.next();
        
        System.out.print("Amount you paid: ");
        double amountPaid = sc.nextDouble();
        
        System.out.print("Payment Method: ");
        String paymentMethod = sc.next();

        // Insert the bill record
        String sql = "INSERT INTO BillRecord (User_ID, Ub_ID, Date_Paid, Amount_Paid, Payment_Method) VALUES (?,?,?,?,?)";
        conf.addRecord(sql, userId, billId, datePaid, amountPaid, paymentMethod);
        
        System.out.println("Bill record added successfully!");
    }

    private int getValidUserId(Scanner sc, CONFIG conf) {
        int userId;
        while (true) {
            System.out.print("Enter User ID: ");
            if (sc.hasNextInt()) {
                userId = sc.nextInt();
                if (conf.getSingleValues("SELECT User_ID FROM User WHERE User_ID = ?", userId) != 0) {
                    return userId;
                } else {
                    System.out.println("Selected User doesn't exist.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid numeric User ID.");
                sc.next();
            }
        }
    }

    private int getValidBillId(Scanner sc, CONFIG conf) {
        int billId;
        while (true) {
            System.out.print("Enter Utility Bill ID: ");
            if (sc.hasNextInt()) {
                billId = sc.nextInt();
                if (conf.getSingleValues("SELECT Ub_ID FROM UtilityBills WHERE Ub_ID = ?", billId) != 0) {
                    return billId;
                } else {
                    System.out.println("Selected Utility Bill doesn't exist.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid numeric Utility Bill ID.");
                sc.next();
            }
        }
    }

    public void viewRecords() {
        String query = "SELECT br.BillRecord_ID, u.First_Name, u.Last_Name, ub.Type, ub.Name_Of_Company, br.Date_Paid, br.Amount_Paid, br.Payment_Method " +
                       "FROM BillRecord br " +
                       "INNER JOIN User u ON br.User_ID = u.User_ID " +
                       "INNER JOIN UtilityBills ub ON br.Ub_ID = ub.UB_ID";

        String[] headers = {"Bill Record ID", "First Name", "Last Name", "Bill Type", "Company Name", "Date Paid", "Amount Paid", "Payment Method"};
        String[] columns = {"BillRecord_ID", "First_Name", "Last_Name", "Type", "Name_Of_Company", "Date_Paid", "Amount_Paid", "Payment_Method"};

        CONFIG conf = new CONFIG();
        conf.viewRecords(query, headers, columns);
    }

    public void viewBillRecords() {
        CONFIG conf = new CONFIG();
        
        System.out.println("   Bill Records List: ");
        String query = "SELECT * FROM BillRecord";
        String[] headers = {"Bill Record ID", "User ID", "Utility Bill ID", "Date Paid", "Amount Paid", "Payment Method", "Status"};
        String[] columns = {"BillRecord_ID", "User_ID", "Ub_ID", "Date_Paid", "Amount_Paid", "Payment_Method", "Status"};
        
        conf.viewRecords(query, headers, columns);
    }

    public void mainBillRecord() {
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();
        String res;

        do {
            System.out.println("=====================================");
            System.out.println("|         BILL RECORD MENU          |");
            System.out.println("=====================================");
            System.out.println("1. Add a Bill Record");
            System.out.println("2. View Bill Records");
            System.out.println("3. Update Bill Record");
            System.out.println("4. Delete Bill Record");
            System.out.println("5. Exit");

            int choice = getChoice(sc);

            switch (choice) {
                case 1:
                    addBillRecord();
                    break;
                case 2:
                    viewRecords();
                    break;
                case 3:
                    updateBillRecord(sc, conf);
                    break;
                case 4:
                    deleteBillRecord(sc, conf);
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

    private int getChoice(Scanner sc) {
        int choice;
        while (true) {
            System.out.print("Enter choice: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
            }
        }
    }

    private void updateBillRecord(Scanner sc, CONFIG conf) {
        viewRecords();
        String sqlUpdate = "UPDATE BillRecord SET Date_Paid = ?, Amount_Paid = ?, Payment_Method = ? WHERE User_ID = ? AND Ub_ID = ?";
        
        int userId = getValidUserId(sc, conf);
        int billId = getValidBillId(sc, conf);

        System.out.print("Enter new Date Paid: ");
        String newDatePaid = sc.next();
        System.out.print("Enter new Amount Paid: ");
        double newAmountPaid = sc.nextDouble();
        System.out.print("Enter new Payment Method: ");
        String newPaymentMethod = sc.next();

        conf.updateRecord(sqlUpdate, newDatePaid, newAmountPaid, newPaymentMethod, userId, billId);
        System.out.println("Bill record updated successfully!");
    }

    private void deleteBillRecord(Scanner sc, CONFIG conf) {
        viewRecords();
        String sqlDelete = "DELETE FROM BillRecord WHERE BillRecord_ID = ?";
        
        int billRecordId;
        while (true) {
            System.out.print("Enter Bill Record ID to delete: ");
            if (sc.hasNextInt()) {
                billRecordId = sc.nextInt();
                if (conf.getSingleValues("SELECT BillRecord_ID FROM BillRecord WHERE BillRecord_ID = ?", billRecordId) != 0) {
                    conf.deleteRecord(sqlDelete, billRecordId);
                    System.out.println("Bill record deleted successfully!");
                    break;
                } else {
                    System.out.println("Selected Bill Record doesn't exist.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid numeric Bill Record ID.");
                sc.next();
            }
        }
    }
}
