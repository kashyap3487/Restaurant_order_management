import java.util.Scanner;
import java.io.FileNotFoundException;

//class for placing order either dine in or take out
public class OrderPlace {

    private static Scanner input = new Scanner(System.in);
    private static int tableNumber;

    public static void dineIn() {
        try{
            boolean flag = true;

            Bill myBill = new Bill("DishItems.txt");       //Bill class instance
            System.out.println("Enter table number: ");
            tableNumber = input.nextInt();

            Bill.menu();    //call menu method from Bill class which display menu
            
            while(flag){
                System.out.println("Press you want to order: ");
                int choice = input.nextInt();

                //make choices from menu
                if(choice == 1){
                    System.out.println("How many Sandwitch do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("Sandwitch", quantity);
                }
                else if(choice == 2){
                    System.out.println("How many Pizza do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("Pizza", quantity);
                }
                else if(choice == 3){
                    System.out.println("How many French Fries do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("French Fries", quantity);
                }
                else if(choice == 4){
                    System.out.println("How many Salad do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("Salad", quantity);
                }
                else if(choice == 5){
                    System.out.println("How many Coffee do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("Coffee", quantity);
                }
                else if (choice == 6){
                    myBill.getTotal();      //call getTotal method from Bill class to make payment
                    flag = false;
                    break;
                }
                else{
                    System.out.println("Choose appropriate item from the menu:");
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("I could not find the file you specified!");
            System.out.println(e.getMessage());
        }
    }

    public static void takeOut() {
        try{
            boolean flag = true;

            Bill myBill = new Bill("DishItems.txt");       //Bill class instance

            Bill.menu();        //call menu method from Bill class which display menu

            while(flag){
                System.out.println("Press you want to order: ");
                int choice = input.nextInt();

                //make choices from menu
                if(choice == 1){
                    System.out.println("How many Sandwitch do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("Sandwitch", quantity);
                }
                else if(choice == 2){
                    System.out.println("How many Pizza do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("Pizza", quantity);
                }
                else if(choice == 3){
                    System.out.println("How many French Fries do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("French Fries", quantity);
                }
                else if(choice == 4){
                    System.out.println("How many Salad do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("Salad", quantity);
                }
                else if(choice == 5){
                    System.out.println("How many Coffee do you want?: ");
                    int quantity = input.nextInt();

                    myBill.addOrder("Coffee", quantity);
                }
                else if (choice == 6){
                    myBill.getTotal();      //call getTotal method from Bill class to make payment
                    flag = false;
                    break;
                }
                else{
                    System.out.println("Choose appropriate item from the menu:");
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("I could not find the file you specified!");
            System.out.println(e.getMessage());
        } 
    }
}