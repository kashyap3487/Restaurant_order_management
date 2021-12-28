import java.io.BufferedReader;  //to read data from text file
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;   // Import the FileNotFoundException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.math.BigDecimal;    //to convert double value upto fix decimal point (operations for arithmetic, scale manipulation, rounding, comparison, hashing, and format conversion)
import java.math.RoundingMode;  //rounding behavior for numerical operations capable of discarding precision

public class Bill {
    private ArrayList<String>orderedFood;       //represents ordered food
    private ArrayList<Integer>orderedQuantity;      //represents the quantities of ordered food
    private ArrayList<String>dish;
    private ArrayList<String>cost;
    public static String userName = "Bhumika";
    public static String userPassword = "123";
    private Double tax = 11.0; //In percentage
    

    private static File f = new File("E:\\COLLEGE_STUDY\\sem_2\\CSD 3464_1 Java\\Final Project\\code\\DishItems.txt");
    private static File f1 = new File("E:\\COLLEGE_STUDY\\sem_2\\CSD 3464_1 Java\\Final Project\\code\\OrderList.txt");

    //constructor for Bill classs
    public Bill(String filename) throws FileNotFoundException{
        //arraylists created here
        orderedFood = new ArrayList<String>();      //stores string references for the ordered food
        orderedQuantity = new ArrayList<Integer>();     //stores string references for the ordered food quantities
        dish = new ArrayList<String>();
        cost = new ArrayList<String>();
        

        Scanner s = new Scanner(f);
        Scanner s1 = new Scanner(f1);

        for(;s.hasNextLine();){             //Goes through all the lines in the DishItems file
            String line = s.nextLine();     //Get a line from DishItems
            String[] z = line.split(",");
            for(int i=0;i<z.length;i++){
                switch(i)
                {
                    case 0:
                        dish.add(z[i]);
                        break;
                    case 1:
                        cost.add(z[i]);
                }
            }
        }
    }

    //instance variable to remember the total cost of bill
    private double totalCost;
    
    //get method that returns the total cost of the bill
    public double getTotal(){
        
        Scanner s2 = new Scanner(System.in);
        getOrder();
        
        System.out.println("Do you want to proceed?(Y/N): ");
		String str2 = s2.nextLine();

		if(str2.equalsIgnoreCase("Y"))
        {
            try {
                //RoundingMode.HALF_UP: Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round up

                //BigDecimal setScale(int newScale, RoundingMode roundingMode)

                //This method returns a BigDecimal whose scale is the specified value, 
                //and whose unscaled value is determined by multiplying or dividing this BigDecimal's unscaled value by the appropriate power of ten to maintain its overall value.
                
                BigDecimal bd = new BigDecimal(totalCost).setScale(2, RoundingMode.HALF_UP);
                double newfinalcost = bd.doubleValue();

                //file operation to store confirmed order
                FileWriter fw1 = new FileWriter(f1,true);
                for (int i=0; i<orderedFood.size();i++){
                    fw1.write(orderedFood.get(i) +","+ orderedQuantity.get(i) +","+ newfinalcost +" ");
                }
                fw1.write("\n");
                fw1.close();
            } 
            catch(IOException e){
                System.out.println("An error occured");
                e.printStackTrace();
            }
            
            System.out.print("Enter Payment Amount: ");     //ask user to make payment
            Double pay = s2.nextDouble();

            if (pay>totalCost)
            {   //print the final receipt after payment accepted
                Double returnTotal = pay - totalCost;
                System.out.println("***************************************************");
                System.out.println("                    RECEIPT                        ");
                System.out.println("***************************************************");
                System.out.println("                  INDIAN TASTE                     ");
                System.out.println("                10004 Main Street                  ");
                System.out.println("                  352-430-1377                     ");
                System.out.println("---------------------------------------------------");
                for (int i=0; i<orderedFood.size();i++){
                    System.out.println(orderedFood.get(i) + " " + orderedQuantity.get(i));
                }
                System.out.println("---------------------------------------------------");
                System.out.println(String.format("                           Total tax amount: %.2f" , (totalCost * tax)/100));
                System.out.println(String.format("                       Total amount payable: %.2f" , totalCost));
                System.out.println(String.format("                          Total amount paid: %.2f" , pay));
                System.out.println(String.format("                          Costumer's return: %.2f", returnTotal));
                System.out.println("-------------Thank you, see you again-------------");
            }
            else
            {   //print the final receipt after payment accepted
                System.out.println("***************************************************");
                System.out.println("                    RECEIPT                        ");
                System.out.println("***************************************************");
                System.out.println("                  INDIAN TASTE                     ");
                System.out.println("                10004 Main Street                  ");
                System.out.println("                  352-430-1377                     ");
                System.out.println("---------------------------------------------------");
                for (int i=0; i<orderedFood.size();i++){
                    System.out.println(orderedFood.get(i) + " " + orderedQuantity.get(i));
                }
                System.out.println("---------------------------------------------------");
                System.out.println(String.format("                           Total tax amount: %.2f" , (totalCost * tax)/100));
                System.out.println(String.format("                       Total amount payable: %.2f" , totalCost));
                System.out.println(String.format("                          Total amount paid: %.2f" , pay));
                System.out.println("-----------Thank you, see you again-------------");
            }
        }
        return totalCost;
    }

    //method to setup bill
    //String[] dish - dishes provided by the restuarant and double[] cost - cost of them
    public void addOrder(String meal, int quantity){
        orderedFood.add(meal);
        orderedQuantity.add(quantity);      //autoboxing done here

        //match the meal string against the dish array and if that meal is present in tha array pick up the corresponding cost of that meal and multiply with given quantity to get total cost
        for (int i=0; i<dish.size(); i++){
            if (meal.equals(dish.get(i))){
                //totalCost is initialized to be zero when a Bill instance is constructed
                Double price = Double.parseDouble(cost.get(i));
                totalCost += quantity * price;
            }
        }
    }

    //method to print out the order for particular costumer
    public void getOrder(){  
        //create loop over all the food mentioned in the ordered food arraylist
        for (int i=0; i<orderedFood.size();i++){
            System.out.println(orderedFood.get(i) + " " + orderedQuantity.get(i));
        }
        totalCost = totalCost + (totalCost * (tax/100));
        System.out.println(String.format("Total amount payable: %.2f" , totalCost));
    }

    //Admin login
    public static void login(){
        ArrayList<String>orderList = new ArrayList<String>();
        
        Scanner keyboard = new Scanner(System.in);

        System.out.println("-_-_-_-_-_-_-_-_-_-_LOGIN-_-_-_-_-_-_-_-_-_-_-_-_");

        System.out.print("Enter User Name:");       //username
        String inputName = keyboard.nextLine();

        System.out.print("Enter User Password:");       //password
        String inputPassword = keyboard.nextLine();

        if(inputName.equals(userName) && inputPassword.equals(userPassword))
        {
            System.out.println("Welcome " + userName);

            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
            System.out.println("1. Add items to the file: ");
            System.out.println("2. Find items from the file: ");
            int num = keyboard.nextInt();

            switch(num){
                case 1:     //to enter new dish into database
                    Scanner key = new Scanner(System.in);
                    System.out.println("Enter dish name: ");
                    String dishName = key.nextLine();

                    System.out.println("Enter dish price: ");
                    String dishPrice = key.nextLine();

                    try{
                        FileWriter fw = new FileWriter(f,true);
                        fw.write("\n"+dishName+","+dishPrice);
                        System.out.println("Food Data Updated.");
                        fw.close();
                    }
                    catch(IOException e){
                        System.out.println("An error occured");
                        e.printStackTrace();
                    }
                    break;
                case 2:     //find order history from database
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(f1));
                        Scanner s3 = new Scanner(f1);
                        for(;s3.hasNextLine();){
                            String str1 = s3.nextLine();
                            orderList.add(str1);
                        }
                        reader.close();
                    } catch (Exception e) {
                        System.out.println("An error occured");
                        e.printStackTrace();
                    }
                    
                    System.out.println("Enter order number: ");
                    int ordernum = keyboard.nextInt();
                    ordernum--;
                    for(int i=0;i<orderList.size();i++){
                        if(ordernum==i){
                            System.out.println(orderList.get(i));
                        }
                    }
            }
        }
        else
        {
            System.out.print("Invalid Username or Password. Please try again");
        }
    }

    //method to display the restaurant menu
    public static void menu(){
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("\tWelcome to Our Restaurant");
        System.out.println("\tRestaurant Menu :");
        System.out.println("\t1.Sandwitch BDT $6.5");
        System.out.println("\t2.Pizza  BDT $10.0");
        System.out.println("\t3.French Fries  BDT $4.0");
        System.out.println("\t4.salad  BDT $3.5");
        System.out.println("\t5.Coffee BDT $3.2");
        System.out.println("\t6.Go to Payment");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
    }
}
