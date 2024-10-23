package billpayments;

import java.util.Scanner;

public class USER {
    public void addBill() {
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();

        System.out.print("Enter User Name: ");
        String userName = sc.nextLine();
        System.out.print("Enter Bill Name: ");
        String billName = sc.nextLine();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Enter Payment Date (YYYY-MM-DD): ");
        String paymentDate = sc.nextLine();
        System.out.print("Enter Payment Method: ");
        String paymentMethod = sc.nextLine();

        String sql = "INSERT INTO Users (u_name, bill_name, amount, payment_date, payment_method) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, userName, billName, amount, paymentDate, paymentMethod);
        System.out.println("Bill added successfully.");
    }

    public void viewBills() {
        String query = "SELECT * FROM User";
        String[] headers = {"Bill ID", "User Name", "Bill Name", "Amount", "Payment Date", "Payment Method"};
        String[] columns = {"bill_id", "user_name", "bill_name", "amount", "payment_date", "payment_method"};

        CONFIG conf = new CONFIG();
        conf.viewRecords(query, headers, columns);
    }

    public void updateBill() {
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();

        System.out.print("Enter Bill ID to Update: ");
        int billId = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter New User Name: ");
        String userName = sc.nextLine();
        System.out.print("Enter New Bill Name: ");
        String billName = sc.nextLine();
        System.out.print("Enter New Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Enter New Payment Date (YYYY-MM-DD): ");
        String paymentDate = sc.nextLine();
        System.out.print("Enter New Payment Method: ");
        String paymentMethod = sc.nextLine();

        String sql = "UPDATE Users SET user_name = ?, bill_name = ?, amount = ?, payment_date = ?, payment_method = ? WHERE bill_id = ?";
        conf.updateRecord(sql, userName, billName, amount, paymentDate, paymentMethod, billId);
        System.out.println("Bill updated successfully.");
    }

    public void deleteBill() {
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();

        System.out.print("Enter Bill ID to Delete: ");
        int billId = sc.nextInt();

        String sql = "DELETE FROM bills WHERE bill_id = ?";
        conf.deleteRecord(sql, billId);
        System.out.println("Bill deleted successfully.");
    }
}

