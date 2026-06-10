package main.java.parte1_filosofos.deadlock;

public class Filosofo extends Thread {
    private final int id;
    private final Garfo esquerdo;
    private final Garfo direito;

    public Filosofo(int id, Garfo esquerdo, Garfo direito){
        this.id = id;
        this.esquerdo = esquerdo;
        this.direito = direito;
    }

    @Override
    public void run(){
        try{
            while (true){
                System.out.println("Filósofo " + id + " está pensando!");
                Thread.sleep(1000);
                System.out.println("Filósofo " + id + " está com fome!");

                esquerdo.pegar();
                System.out.println("O filósofo " + id + " pegou o garfo esquerdo - " +esquerdo.getId());

                Thread.sleep(200);

                direito.pegar();
                System.out.println("Filósofo " + id + " pegou o garfo direito - " + direito.getId());
                Thread.sleep(200);

                System.out.println("FIlósofo " + id + " está comendo!");
                Thread.sleep(1000);

                direito.largar();
                esquerdo.largar();
                System.out.println("Filósofo " + id + " terminou de comer");
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("Filósofo " + id + " foi interrompido.");
        }
    }
}