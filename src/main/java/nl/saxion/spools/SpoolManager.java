package nl.saxion.spools;

public class SpoolManager {
    private static SpoolManager INSTANCE;

    private SpoolManager(){

    }

    public static SpoolManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new SpoolManager();
        }
        return INSTANCE;
    }
}
