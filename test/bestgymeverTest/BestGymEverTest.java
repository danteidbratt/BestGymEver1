
package bestgymeverTest;

import bestgymever.*;
import java.time.LocalDate;
import java.util.*;
import junit.framework.TestCase;
import org.junit.Test;

public class BestGymEverTest {
    
    BestGymEver bge = new BestGymEver();
    CustomerData cd = new CustomerData();
    
    @Test
    public final void isActiveTest(){
        Customer c1 = new Customer("3333333333", "Kalle Svensson",
                                   LocalDate.parse("2017-03-03")); 
        Customer c2 = new Customer("4444444444", "Jalle Kvensson",
                                   LocalDate.parse("2017-05-10"));
        Customer c3 = new Customer("5555555555", "Nalle Tvensson",
                                   LocalDate.parse("2015-06-08"));
        Customer c4 = new Customer("6666666666", "Salle Dvensson",
                                   LocalDate.parse("2016-08-20"));
        
        TestCase.assertTrue(cd.isActive(c1));
        TestCase.assertTrue(cd.isActive(c2));
        TestCase.assertFalse(cd.isActive(c3));
        TestCase.assertFalse(cd.isActive(c4));
    }
    
    @Test
    public final void pickCustomerTest(){
        String s1 = "1234567890, Jimmy Zimmy\n1990-08-21";
        String s2 = "0987654321, Tammy Dammy\n2017-04-11";
        String s3 = "0192837465, Pimmy Limmy\n2016-11-22";
        String s4 = "6574839201, Himmy Wimmy\n2015-10-15";
        
        TestCase.assertEquals(cd.pickCustomer(s1).getNamn(), "Jimmy Zimmy");
        TestCase.assertTrue(cd.pickCustomer(s2).getNamn().equalsIgnoreCase("Tammy Dammy"));
        TestCase.assertFalse(cd.pickCustomer(s3).getPersonnummer().equalsIgnoreCase("6574839201"));
        TestCase.assertFalse(cd.pickCustomer(s4).getSenasteBetalning().toString().equalsIgnoreCase("2011-11-11"));
    }
    
    @Test
    public final void isCustomerTest(){
        List<Customer> cl = new ArrayList<> ();
        
        Customer c1 = new Customer("3333333333", "Kalle Svensson",
                                   LocalDate.parse("2017-03-03")); 
        Customer c2 = new Customer("4444444444", "Jalle Kvensson",
                                   LocalDate.parse("2017-05-10"));
        Customer c3 = new Customer("5555555555", "Nalle Tvensson",
                                   LocalDate.parse("2015-06-08"));
        Customer c4 = new Customer("6666666666", "Salle Dvensson",
                                   LocalDate.parse("2016-08-20"));
        
        cl.add(c1);
        cl.add(c2);
        cl.add(c3);
        cl.add(c4);
        
        TestCase.assertTrue(cd.isCustomer("3333333333", cl));
        TestCase.assertTrue(cd.isCustomer("Jalle Kvensson", cl));
        TestCase.assertFalse(cd.isCustomer("Adam Adam", cl));
        TestCase.assertFalse(cd.isCustomer("1010101010", cl));
    }
}
