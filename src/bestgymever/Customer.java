package bestgymever;

import java.time.*;
import java.util.*;

public class Customer {
    
    Scanner input = new Scanner(System.in);
    protected String personnummer;
    protected String namn;
    protected LocalDate betalning;
    
    public Customer(String personnummer, String namn, LocalDate betalning){
        this.personnummer = personnummer;
        this.namn = namn;
        this.betalning = betalning;
    }
    
    public String getPersonnummer(){
        return personnummer;
    }
    public String getNamn(){
        return namn;
    }
    public LocalDate getSenasteBetalning(){
        return betalning;
    }
}