
package bestgymever;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomerData {
    
    protected int customerIndex;
    protected Customer visitor;
    protected boolean active;
    
    public CustomerData(){
        System.out.println(Design.START.line);
        List<Customer> customers;
        customers = getCustomers();
        while (true){
            String nameOrNumber = visitorInput();
            if(isCustomer(nameOrNumber, customers)){
                visitor = customers.get(customerIndex);
                if(isActive(visitor))
                    registerWorkout(visitor);
            } else {
                System.out.println(nameOrNumber + " finns ej i kundregistret.\n");
            }
            System.out.println(Design.SPLIT.line);
        }
    }
    
    /**
     * Returns a List containing all customers registered in "Customers.txt". 
     * @return 
     */
    public List<Customer> getCustomers(){
        StringBuilder tempS = new StringBuilder();
        List<Customer> customers = new ArrayList();
        
        try(Scanner theList = new Scanner(new FileReader("Customers.txt"))) {
            while (theList.hasNext()) {
                tempS.append(theList.nextLine());
                tempS.append("\n");
                tempS.append(theList.nextLine());
                Customer x = pickCustomer(tempS.toString());
                customers.add(x);
                tempS.delete(0, tempS.length());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return customers;
    }
    
    /**
     * Creates a Customer object.
     * @param tempS
     * @return 
     */
    public Customer pickCustomer(String tempS){
        Scanner sc = new Scanner(tempS);
        String tempP = sc.next();
        tempP = tempP.substring(0, tempP.length()-1);
        String tempN = sc.nextLine().trim();
        LocalDate tempB = LocalDate.parse(sc.nextLine().trim());
        Customer x = new Customer(tempP, tempN, tempB);
        return x;
    }
    
    /**
     * Asks user to input name or personal number.
     * Exit program if input is empty.
     * @return 
     */
    public String visitorInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Ange namn eller personnummer:\t");
        String nameOrNumber = input.nextLine();
        if(nameOrNumber.equals("")){
            System.out.println(Design.STOP.line);
            System.exit(0);
        }
        return nameOrNumber;
    }
    
    /**
     * Checks is user input matches any registered customer if "Customer.txt".
     * @param visitor
     * @param customers
     * @return 
     */
    public boolean isCustomer(String visitor, List<Customer> customers){
        for (int i = 0; i < customers.size(); i++) {
            if(visitor.equalsIgnoreCase(customers.get(i).getNamn()) 
            || visitor.equalsIgnoreCase(customers.get(i).getPersonnummer())){
                customerIndex = i;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if customer is active.
     * @param customer
     * @return 
     */
    public boolean isActive(Customer customer){
        active = customer.betalning.compareTo(LocalDate.now().minusYears(1)) > 0;
        if(active)
            System.out.println(customer.namn + " är en betalande kund.\n");
        else
            System.out.println(customer.namn + " är en före detta kund.\n");
        return active;
    }
    
    public void registerWorkout(Customer customer){
        File visitorList = new File("Workouts.txt");
        try(PrintWriter printer = new PrintWriter(
                                  new BufferedWriter(
                                  new FileWriter(visitorList, true)))) {
            if(!visitorList.exists())
                visitorList.createNewFile();
            printer.print(getPrintableString(customer));
            printer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private String getPrintableString(Customer customer){
        String customerInfo = String.format("\nNamn:\t\t   %s\nPersonnummer:\t   %s\nTränade senast:\t   %s\n", 
                              customer.getNamn(),
                              customer.getPersonnummer(), 
                              LocalDateTime.now().format(
                              DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
        return customerInfo;
    }
}
