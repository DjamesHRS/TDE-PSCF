package parte1_filosofos.solucao;

import parte1_filosofos.deadlock.Garfo;

public class Filosofo extends Thread{
    private final int id;
    private final Garfo primeiro;
    private final Garfo segundo;

    public Filosofo (int id, Garfo esquerdo, Garfo direito){
        this.id = id;
        if (esquerdo.getId() < direito.getId()){
            this.primeiro = esquerdo;
            this.segundo = direito;
        } else {
            this.primeiro = direito;
            this.segundo = esquerdo;
        }
    }

    @Override
    public void run(){
        try{
            while(true){
                System.out.println("Filósofo " + id + " está pensando...");
                Thread.sleep(1000);

                System.out.println("Filósofo " + id + " está com fome");

                primeiro.pegar();
                System.out.println("Filósofo " + id + " pegou garfo " + primeiro.getId());

                segundo.pegar();
                System.out.println("Filósofo " + id + " pegou garfo " + segundo.getId());

                System.out.println("Filósofo " + id + " está comendo");
                Thread.sleep(1000);

                segundo.largar();
                primeiro.largar();
                System.out.println("Filósofo " + id + " terminou de comer");
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("Filósofo " + id + " foi interrompido.");
        }
    }

}
