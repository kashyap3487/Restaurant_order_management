import java.util.Scanner;
//main class which run the application

public class Driver {
    private static Scanner input = new Scanner(System.in);
    public static void main(String args[]){

        boolean flag1 = true;
       
        while(flag1){
            //call Menumain method
            Menumain();
            System.out.println("Enter your choice: ");
            int select = input.nextInt();

            switch(select){
                case 1:
                    //call manageorder method to make/place the order
                    manageOrder();
                    System.out.println("Enter your choice: ");
                    int option = input.nextInt();

                    switch(option){
                        case 1:
                            //call method to place order for dine in
                            OrderPlace.dineIn();
                            break;

                        case 2:
                            //call method to place order for take out
                            OrderPlace.takeOut();
                            break;

                        default:
                            break;
                    }
                    break;

                case 2:
                    //call method for admin login
                    Bill.login();
                    break;

                case 3:
                    flag1 = false;
                    break;

                default:
                    flag1 = false;
                    break;
            }
        }
    }

    public static void manageOrder(){
        System.out.println("-------MANAGE ORDER MENU----------");
		System.out.println("1. Dine In Order");
		System.out.println("2. Take Out Order");
    }

    public static void Menumain() {
		System.out.println("-------MENU MAIN----------");
		System.out.println("1. Manage order");
		System.out.println("2. Admin Login");
		System.out.println("3. Exit");
	}
}
