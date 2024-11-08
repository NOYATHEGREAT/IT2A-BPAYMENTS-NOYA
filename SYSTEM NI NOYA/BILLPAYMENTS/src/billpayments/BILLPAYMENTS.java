
package billpayments;

import static java.lang.System.exit;
import java.util.Scanner;

public class BILLPAYMENTS {
    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in);
        
        boolean exit = true;
        do{
 
         System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("=========================================");
            System.out.println("|         BILL  MANAGEMENT              |");
            System.out.println("=========================================");
            System.out.println("");
            System.out.println("          Please select an option:       ");
            System.out.println("-----------------------------------------");
            System.out.println("    1. User");
            System.out.println("    2. Bills");
            System.out.println("    3. Bill Records");
            System.out.println("    4. Staff Only");
            System.out.println("    5. Exit the Application ");
            System.out.println("-----------------------------------------");
            System.out.println("");
        
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
       
        switch(choice){
            case 1:
                USER us  = new USER();
                us.mainUser();
                break;
            case 2:
                UtilityBills ub = new UtilityBills();
                ub.mainBills();
                break;
            case 3:
                 BillRecord br1 = new BillRecord();
                 br1.mainBillRecord();
                break;
            case 4:
               System.out.print("Are you a Staff? Yes or No: ");
                    String res = sc.next();

                    if (res.equalsIgnoreCase("yes")) {
                        System.out.print("Enter Staff password: ");
                        String hrPassword = sc.next();

                        final String STAFF_PASSWORD = "staff1234";

                        if (hrPassword.equals(STAFF_PASSWORD)) {
                            StatusBill sts1 = new StatusBill();
                           sts1.Status();
                        } else {
                            System.out.println("Invalid Staff password. Access denied.");
                        }
                    } else {
                        System.out.println("You do not have permission to access Staff-only features.");
                    }
                break;    
            case 5:
                System.out.print("Do you want to log out? Yes or No: ");
                String response = sc.next();
                if(response.equalsIgnoreCase("yes")){
                    exit = false;
                }
                break;
            
            
        }
          
          
        }while(exit);
 
        System.out.println("Thank you for using the Application ! ");
        
    }
}

 

