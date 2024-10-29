
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
            
            System.out.println("=====================================");
            System.out.println("|           STATUS MENU            |");
            System.out.println("=====================================");
            System.out.println("1. Edit Status");
            System.out.println("2. View Status");
            System.out.println("3. Log Out");
            System.out.println("=====================================");
      
            
            int choice; 
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            
            
            switch(choice){
                case 1: 
                    
                    System.out.println(" - USER LIST - ");
                    USER us = new USER();
                    us.viewUser();
                    System.out.println(" - UTILITY BILL LIST - ");
                    UtilityBills ub = new UtilityBills();
                    ub.viewBills();
                    System.out.println(" - BILL RECORD LIST - ");
                    BillRecord br = new BillRecord();
                    br.viewBillRecords();
                    String sqlup = "UPDATE BillRecord SET Status = ? WHERE User_ID = ? AND Ub_ID = ?";
                    
                    System.out.print("Enter User ID: ");
                    int ids = sc.nextInt();
                    System.out.print("Enter Utility Bill ID: ");
                    int ids1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Status: ");
                    String stats = sc.nextLine();
                    conf.updateRecord(sqlup, stats,ids,ids1);
                    break;
                case 2:
                    br = new BillRecord();
                    br.viewBillRecords();
                    break;
                case 3:
                    System.out.println("Exiting.....");
                    break;
        
                
            }
            
            System.out.println("");
            System.out.print("Do you want to continue? Yes or No: ");
            res = sc.next();
        }while(res.equalsIgnoreCase("yes"));
           
        
    }
    
    
    
    
}
