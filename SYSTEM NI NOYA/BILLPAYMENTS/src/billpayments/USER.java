package billpayments;
import java.util.Scanner;
public class USER {

    public void addUser() {
        
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();

        System.out.println("\n=====================================");
        System.out.println("|           ADD USER                |");
        System.out.println("=====================================");
        System.out.print("Enter First Name: ");
        String fname = sc.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lname = sc.nextLine();
        
        System.out.print("Address: ");
        String add = sc.nextLine();  
        
        int age;
        while (true) {
            System.out.print("Age: ");
            if (sc.hasNextInt()) { 
                age = sc.nextInt();  
                break; 
            } else {
                System.out.println("Invalid input! Please enter a valid integer for age.");
                sc.next(); 
            }
        }
        
        
      String cont1 = ""; 
        
        while (true) {
            System.out.print("Contact No: ");
            
          
            if (sc.hasNext()) {  
                cont1 = sc.next(); 
              
            if (cont1.matches("\\d+")) {  
                    break;  
                } else {
                    System.out.println("Invalid input! Please enter a valid numeric contact number.");
                }
            }
        }
        
        
        
        System.out.print("Email Address: ");
        String email = sc.next();  
      
        
        String sql = "INSERT INTO User (First_Name, Last_Name, Address, Age, Contact_No, Email) VALUES (?, ?, ?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, add, age, cont1, email);
        
        
    }

    public void viewUser() {
        String query = "SELECT * FROM User";
        String[] headers = {"ID", "First Name", "Last Name", "Address", "Age", "Contact No.", "Email Address"};
        String[] columns = {"User_ID", "First_Name", "Last_Name", "Address", "Age", "Contact_No", "Email"};

        CONFIG conf = new CONFIG();
        conf.viewRecords(query, headers, columns);
    }

    public void mainUser() {
        USER us = new USER();
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();

        String res;
        do {
            System.out.println("\n=====================================");
            System.out.println("|           USER MENU               |");
            System.out.println("=====================================");
            System.out.println("|     1. Add User                   |");
            System.out.println("|     2. View User                  |");
            System.out.println("|     3. Update User                |");
            System.out.println("|     4. Delete User                |");
            System.out.println("|     5. Exit                       |");
            System.out.println("=====================================");
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
                    us.addUser();
                    break;
                case 2:
                    us.viewUser();
                    break;
                case 3:
                    us.viewUser();
                    String sqlUpdate = "UPDATE User SET Address = ?, Age = ?, Contact_No = ?, Email = ? WHERE User_ID = ?";

                    int ID;
                    while (true) {
                        System.out.print("Enter User ID to update: ");
                        if (sc.hasNextInt()) {
                            ID = sc.nextInt();
                            if (conf.getSingleValues("SELECT User_ID FROM User WHERE User_ID = ?", ID) != 0) {
                                break;
                            } else {
                                System.out.println("Selected User doesn't exist.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid numeric User ID.");
                            sc.next();
                        }
                    }
                    
                    
                     sc.nextLine();
                     
                    System.out.print("Enter new Address: ");
                    String newadd = sc.nextLine();
                    
                 
                            int newage;
                 while (true) {
                      System.out.print("Enter new Age: ");
                     if (sc.hasNextInt()) { 
                         newage = sc.nextInt();  
                         break; 
                     } else {
                         System.out.println("Invalid input! Please enter a valid integer for age.");
                         sc.next(); 
                     }
                 }
   
             
                  
                                sc.nextLine();

                                 String newcon = ""; 

                    while (true) {
                        System.out.print("Contact No: ");


                        if (sc.hasNext()) {  
                            newcon = sc.next(); 

                        if (newcon.matches("\\d+")) {  
                                break;  
                            } else {
                                System.out.println("Invalid input! Please enter a valid numeric contact number.");
                            }
                        }
                    }

                  sc.nextLine();
          
                    System.out.print("Enter new Email Address: ");
                    String newemailadd = sc.nextLine();

                    conf.updateRecord(sqlUpdate, newadd, newage, newcon, newemailadd, ID);
                    break;
                    
                    case 4:
                    us.viewUser();
                    String sqlDelete = "DELETE FROM User WHERE User_ID = ?";
                    int idUp;
                    while (true) {
                        System.out.print("Enter User ID: ");
                        if (sc.hasNextInt()) {
                            idUp = sc.nextInt();
                            if (conf.getSingleValues("SELECT User_ID FROM User WHERE User_ID = ?", idUp) != 0) {
                                break;
                            } else {
                                System.out.println("Selected User doesn't exist.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid numeric User ID.");
                            sc.next();
                        }
                    }
                    conf.deleteRecord(sqlDelete, idUp);
                    break;
                    
                    case 5:
                    System.out.println("Exiting...");
                    break;
            }
            System.out.println("");
            System.out.print("Do you want to continue on User Management? Yes or No: ");
            res = sc.next();
        } while (res.equalsIgnoreCase("yes"));
    }
}
