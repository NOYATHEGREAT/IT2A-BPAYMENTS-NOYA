
package billpayments;

import java.util.Scanner;

public class BILLPAYMENTS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        USER user = new USER();
        int chose;

        do {
            System.out.println("BILL PAYMENTS");
            System.out.println("1. Add Bill");
            System.out.println("2. View Bills");
            System.out.println("3. Update Bill");
            System.out.println("4. Delete Bill");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");
            int act = sc.nextInt();
            sc.nextLine(); 

            switch (act) {
                case 1:
                    user.addBill();
                    break;

                case 2:
                    user.viewBills();
                    break;

                case 3:
                    user.updateBill();
                    break;

                case 4:
                    user.deleteBill();
                    break;

                case 5:
                    System.out.println("Exiting....... Thank You for using the Billing System!");
                    break;

                default:
                    System.out.println("Action Error: There's no such option");
            }

            if (act != 5) {
                System.out.print("\nEnter Choice Again (1/0)?: ");
                chose = sc.nextInt();
            } else {
                chose = 0; 
            }
        } while (chose != 0);
    }
}

 

