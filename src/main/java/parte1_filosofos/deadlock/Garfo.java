package parte1_filosofos.deadlock;

import java.util.concurrent.Semaphore;

public class Garfo {
    private final int id;
    private final Semaphore semaphore;

    public Garfo (int id){
        this.id = id;
        this.semaphore = new Semaphore(1, true);
    }

    public int getId(){
        return id;
    }

    public void pegar() throws InterruptedException {
        semaphore.acquire();
    }

    public void largar(){
        semaphore.release();
    }
}