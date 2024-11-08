package billpayments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BillRecord {

    public void addBillRecord() {
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();

        System.out.println("=====================================");
        System.out.println("|          ADD BILL RECORD          |");
        System.out.println("=====================================");
        
        USER us = new USER();
        us.viewUser();
        
        System.out.println(" - Select a Utility Bill - ");
        UtilityBills ub = new UtilityBills();
        ub.viewBills();

       

        int userId = getValidUserId(sc, conf); 
        int billId = getValidBillId(sc, conf);

        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String datePaid = currdate.format(format);
        
        System.out.print("Amount you paid: ");
        double amountPaid = sc.nextDouble();
        
        System.out.print("Payment Method: ");
        String paymentMethod = sc.next();
          
        String status = "Pending";
        
        String sql = "INSERT INTO BillRecord (User_ID, Ub_ID, Date_Paid, Amount_Paid, Payment_Method,Status) VALUES (?,?,?,?,?,?)";
        conf.addRecord(sql, userId, billId, datePaid, amountPaid, paymentMethod,status);
        
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

    public void viewAllRecords() {
        String query = "SELECT br.BillRecord_ID, u.First_Name, u.Last_Name, ub.Type, ub.Name_Of_Company, br.Date_Paid, br.Amount_Paid, br.Payment_Method, br.Status " +
                       "FROM BillRecord br " +
                       "INNER JOIN User u ON br.User_ID = u.User_ID " +
                       "INNER JOIN UtilityBills ub ON br.Ub_ID = ub.UB_ID";

        String[] headers = {"Bill Record ID", "First Name", "Last Name", "Bill Type", "Company Name", "Date Paid", "Amount Paid", "Payment Method","Status"};
        String[] columns = {"BillRecord_ID", "First_Name", "Last_Name", "Type", "Name_Of_Company", "Date_Paid", "Amount_Paid", "Payment_Method","Status"};

        CONFIG conf = new CONFIG();
        conf.viewRecords(query, headers, columns);
    }
    public void viewPendingRecords(){
        
         String query = "SELECT br.BillRecord_ID, u.First_Name, u.Last_Name, ub.Type, ub.Name_Of_Company, br.Date_Paid, br.Amount_Paid, br.Payment_Method, br.Status " +
                       "FROM BillRecord br " +
                       "INNER JOIN User u ON br.User_ID = u.User_ID " +
                       "INNER JOIN UtilityBills ub ON br.Ub_ID = ub.UB_ID "
                 + "WHERE br.Status = 'Pending' ";

        String[] headers = {"Bill Record ID", "First Name", "Last Name", "Bill Type", "Company Name", "Date Paid", "Amount Paid", "Payment Method","Status"};
        String[] columns = {"BillRecord_ID", "First_Name", "Last_Name", "Type", "Name_Of_Company", "Date_Paid", "Amount_Paid", "Payment_Method","Status"};

        CONFIG conf = new CONFIG();
        conf.viewRecords(query, headers, columns);
        
        
        
    }
    public void viewNotpaidRecords(){
        
        String query = "SELECT br.BillRecord_ID, u.First_Name, u.Last_Name, ub.Type, ub.Name_Of_Company, br.Date_Paid, br.Amount_Paid, br.Payment_Method, br.Status " +
                       "FROM BillRecord br " +
                       "INNER JOIN User u ON br.User_ID = u.User_ID " +
                       "INNER JOIN UtilityBills ub ON br.Ub_ID = ub.UB_ID "
                 + "WHERE br.Status = 'Not paid' ";

        String[] headers = {"Bill Record ID", "First Name", "Last Name", "Bill Type", "Company Name", "Date Paid", "Amount Paid", "Payment Method","Status"};
        String[] columns = {"BillRecord_ID", "First_Name", "Last_Name", "Type", "Name_Of_Company", "Date_Paid", "Amount_Paid", "Payment_Method","Status"};

        CONFIG conf = new CONFIG();
        conf.viewRecords(query, headers, columns);
      
    }
    public void viewPaidRecords(){
        
  
        
        String query = "SELECT br.BillRecord_ID, u.First_Name, u.Last_Name, ub.Type, ub.Name_Of_Company, br.Date_Paid, br.Amount_Paid, br.Payment_Method, br.Status " +
                       "FROM BillRecord br " +
                       "INNER JOIN User u ON br.User_ID = u.User_ID " +
                       "INNER JOIN UtilityBills ub ON br.Ub_ID = ub.UB_ID "
                 + "WHERE br.Status = 'Paid' ";

        String[] headers = {"Bill Record ID", "First Name", "Last Name", "Bill Type", "Company Name", "Date Paid", "Amount Paid", "Payment Method","Status"};
        String[] columns = {"BillRecord_ID", "First_Name", "Last_Name", "Type", "Name_Of_Company", "Date_Paid", "Amount_Paid", "Payment_Method","Status"};

        CONFIG conf = new CONFIG();
        conf.viewRecords(query, headers, columns);
    }
    public void viewUserById(){
        
        
        
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();
         int userId;
             while (true) {
        System.out.print("Enter User ID to view all Utility Bills they paid for: ");
        if (sc.hasNextInt()) {
            userId = sc.nextInt();
            if (conf.getSingleValues("SELECT User_ID FROM User WHERE User_ID = ?", userId) != 0) {
                break;
            } else {
                System.out.println("User with this ID does not exist.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid numeric User ID.");
            sc.next(); // clear the invalid input
        }
    }

        
        String viewQuery = "SELECT BillRecord.BillRecord_ID, UtilityBills.Type, UtilityBills.Name_Of_Company, BillRecord.Date_Paid, BillRecord.Status FROM BillRecord "
                + "LEFT JOIN UtilityBills ON UtilityBills.UB_ID = BillRecord.Ub_ID "
                + "WHERE BillRecord.User_ID = ?";
        
        String[] head = {"Bill Record ID", "Type", "Name of Company","Date Paid"," Status"};
        String[] column = {"BillRecord_ID","Type","Name_Of_Company","Date_Paid","Status"};
         
       
        conf.viewApplicantss(viewQuery, head, column, userId);
       
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
                    viewAllRecords();
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
        
        
        viewAllRecords();
        String sqlUpdate = "UPDATE BillRecord SET Date_Paid = ?, Amount_Paid = ?, Payment_Method = ? WHERE BillRecord_ID = ?";
      
         int billID;
                while (true) {
                System.out.print("Enter Bill Record ID to Update : ");
                if (sc.hasNextInt()) {
                    billID = sc.nextInt();
                    if (conf.getSingleValues("SELECT BillRecord_ID FROM BillRecord WHERE BillRecord_ID = ?", billID) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Bill Record doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid numeric Bill Record ID.");
                    sc.next(); 
                }
            }
        
        System.out.print("Enter new Date Paid: ");
        String newDatePaid = sc.next();
        System.out.print("Enter new Amount Paid: ");
        double newAmountPaid = sc.nextDouble();
        System.out.print("Enter new Payment Method: ");
        String newPaymentMethod = sc.next();

        conf.updateRecord(sqlUpdate, newDatePaid, newAmountPaid, newPaymentMethod, billID);
        System.out.println("Bill record updated successfully!");
    }

    private void deleteBillRecord(Scanner sc, CONFIG conf) {
        viewAllRecords();
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
