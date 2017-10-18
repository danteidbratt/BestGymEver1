package bestgymever;

public enum Design {
    
    START("************** Best Gym Ever **************\n"),
    SPLIT("-------------------------------------------\n"),
    STOP("\n-------------------------------------------");
    public final String line;
    
    Design(String line){
        this.line = line;
    }
    
    
}
