package billpayments;
import java.util.Scanner;
public class StatusBill {
    
    
    public void Status(){
        Scanner sc = new Scanner(System.in);
          CONFIG conf = new CONFIG();
       String res;
        do{
              System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.println("\n=====================================");
            System.out.println("|           STATUS MENU             |");
            System.out.println("=====================================");
            System.out.println("|    1. Edit Status                 |");
            System.out.println("|    2. View Status                 |");
            System.out.println("|    3. Pending Status              |");
            System.out.println("|    4. Not Paid Status             |");
            System.out.println("|    5. Paid Status                 |");
            System.out.println("|    6. View User by ID             |");
            System.out.println("|    7. Log out                     |");
            System.out.println("=====================================");
      
            int choice;
            while (true) {
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 7) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 7.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next();
                }
            }
            
            
            switch(choice){
                case 1: 
                    
                   
                    System.out.println("  BILL RECORD LIST  ");
                    BillRecord br = new BillRecord();
                    br.viewAllRecords();
                    String sqlup = "UPDATE BillRecord SET Status = ? WHERE BillRecord_ID = ?";
                   
                 
                     int ids;
                while (true) {
                System.out.print("Enter Bill Record ID to Update : ");
                if (sc.hasNextInt()) {
                    ids = sc.nextInt();
                    if (conf.getSingleValues("SELECT BillRecord_ID FROM BillRecord WHERE BillRecord_ID = ?", ids) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Bill Record doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid numeric Bill Record ID.");
                    sc.next(); 
                }
            }
                    sc.nextLine();
                    
                    System.out.print("Enter Status: ");
                    String stats = sc.nextLine();
                    conf.updateRecord(sqlup, stats,ids);
                    break;
                case 2:
                    br = new BillRecord();
                    br.viewAllRecords();
                    break;
                case 3:
                      br = new BillRecord();
                      br.viewPendingRecords();
                    break;
                case 4:
                    br = new BillRecord();
                    br.viewNotpaidRecords();
                    break;
                case 5:
                    br = new BillRecord();
                    br.viewPaidRecords();
                    break;
                case 6:
                     USER us = new USER();
                     us.viewUser();
                      br = new BillRecord();
                      br.viewUserById();
                    break;
            }
            
            System.out.println("");
            System.out.print("Do you want to continue? Yes or No: ");
            res = sc.next();
        }while(res.equalsIgnoreCase("yes"));
           
        
    }
    
    
    
    
}
