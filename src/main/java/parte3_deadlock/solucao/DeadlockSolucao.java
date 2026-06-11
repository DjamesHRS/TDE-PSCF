package parte3_deadlock.solucao;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSolucao {
    private static final Lock LOCK_A = new ReentrantLock();
    private static final Lock LOCK_B = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread( () -> {
            System.out.println("t1 pegando lock A");
            LOCK_A.lock();
            System.out.println("t1 pegou lock A");

            try{
                Thread.sleep(50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("t1 pegando lock B");
            LOCK_B.lock();
            System.out.println("t1 pegou lock B");
            System.out.println("t1 concluiu");
            LOCK_A.unlock();
            LOCK_B.unlock();
        });
        Thread t2 = new Thread( () -> {
            System.out.println("t2 pegando lock A");
            LOCK_A.lock();
            System.out.println("t2 pegou lock A");

            try{
                Thread.sleep(50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("t2 pegando lock B");
            LOCK_B.lock();
            System.out.println("t2 pegou lock B");
            System.out.println("t2 concluiu");
            LOCK_B.unlock();
            LOCK_A.unlock();
        });

        t1.start();
        t2.start();
    }
}
