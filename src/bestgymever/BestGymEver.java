
package bestgymever;

import java.util.*;

public class BestGymEver {
    
    public static void main(String[] args) {
        BestGymEver bge = new BestGymEver();
        bge.program();
    }
    
    public void program (){
        CustomerData cd = new CustomerData();
        List<Customer> customers;
        customers = cd.getCustomers();
        System.out.println(Design.START.line);
        while (true){
            String nameOrNumber = cd.visitorInput();
            if(nameOrNumber.equals(""))
                break;
            if(cd.isCustomer(nameOrNumber, customers)){
                cd.visitor = customers.get(cd.customerIndex);
                cd.active = cd.isActive(cd.visitor);
                if(cd.active)
                    cd.registerWorkout(cd.visitor);
            } else {
                System.out.println(nameOrNumber + " finns ej i kundregistret.\n");
            }
            System.out.println(Design.SPLIT.line);
        }
        System.out.println(Design.STOP.line);
    }
}