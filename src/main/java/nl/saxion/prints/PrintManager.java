package nl.saxion.prints;

public class PrintManager {
    private static PrintManager INSTANCE;

    private PrintManager(){

    }

    public static PrintManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new PrintManager();
        }
        return INSTANCE;
    }
}
